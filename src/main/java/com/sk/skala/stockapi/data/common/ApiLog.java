package com.sk.skala.stockapi.data.common;

public class ApiLog {
    private long timestamp;
    private String remoteAddress;
    private String apiHost;
    private String apiUrl;
    private String apiMethod;
    private String apiController;
    private String requestParams;
    private String requestBody;
    private String responseBody;
    private int apiResult;
    private long elapsedTime;

    // 수동 Setter (에러 방지용)
    public void setTimestamp(long t) { this.timestamp = t; }
    public long getTimestamp() { return timestamp; }
    public void setRemoteAddress(String a) { this.remoteAddress = a; }
    public void setApiHost(String h) { this.apiHost = h; }
    public void setApiUrl(String u) { this.apiUrl = u; }
    public void setApiMethod(String m) { this.apiMethod = m; }
    public void setApiController(String c) { this.apiController = c; }
    public void setRequestParams(String p) { this.requestParams = p; }
    public void setRequestBody(String b) { this.requestBody = b; }
    public void setResponseBody(String b) { this.responseBody = b; }
    public void setApiResult(int r) { this.apiResult = r; }
    public void setElapsedTime(long e) { this.elapsedTime = e; }
}