package com.comany.example.managment.managment.bean;

/**
 * Created by zam zam on 10/12/2019.
 */
public class WsResponse {

    private String responseCode;
    private String responseMessage;
    private Object responseObject;

    public WsResponse(String responseCode, String responseMessage, Object responseObject) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseObject = responseObject;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Object getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(Object responseObject) {
        this.responseObject = responseObject;
    }
}
