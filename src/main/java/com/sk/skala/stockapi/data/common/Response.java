package com.sk.skala.stockapi.data.common;

public class Response {
    private int result;
    private int code;
    private String message;
    private Object body;
    private String error;

    public Response() {}

    public static Response ok(Object body) {
        Response response = new Response();
        response.setResult(0);
        response.setBody(body);
        return response;
    }

    public static Response ok() { return ok(null); }

    // 롬복 없이 수동으로 만든 Getter/Setter
    public int getResult() { return result; }
    public void setResult(int result) { this.result = result; }
    public Object getBody() { return body; }
    public void setBody(Object body) { this.body = body; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}