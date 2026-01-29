package com.sk.skala.stockapi.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sk.skala.stockapi.config.ApplicationProperties;
import com.sk.skala.stockapi.data.common.ApiLog;
import com.sk.skala.stockapi.data.common.Response;
import com.sk.skala.stockapi.tools.HostInfo;
import com.sk.skala.stockapi.tools.JsonTool;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);
    private final ApplicationProperties applicationProperties;

    public LoggingAspect(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ApiLog apiLog = new ApiLog();
        apiLog.setTimestamp(System.currentTimeMillis());

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attr == null) return joinPoint.proceed();
        HttpServletRequest req = attr.getRequest();

        try {
            apiLog.setRemoteAddress(req.getRemoteAddr());
            apiLog.setApiHost(HostInfo.getHostname());
            apiLog.setApiUrl(req.getRequestURI());
            apiLog.setApiMethod(req.getMethod());
            apiLog.setApiController(joinPoint.getSignature().toShortString());

            Object result = joinPoint.proceed();
            if (result instanceof Response) {
                apiLog.setResponseBody(JsonTool.toString(result));
            }
            apiLog.setApiResult(0); 
            return result;
        } catch (Exception e) {
            Response res = new Response();
            res.setResult(-1);
            res.setMessage(e.getMessage());
            apiLog.setApiResult(-1);
            apiLog.setResponseBody(JsonTool.toString(res));
            throw e;
        } finally {
            apiLog.setElapsedTime(System.currentTimeMillis() - apiLog.getTimestamp());
            log.info("API Log: {}", JsonTool.toString(apiLog));
        }
    }
}