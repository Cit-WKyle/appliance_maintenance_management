package com.appl_maint_mngt.web.models.common;

/**
 * Created by Kyle on 15/03/2017.
 */
public class ApiResponse<T> {

    private ApiResponseStatus status;
    private String message;
    private T data;

    public ApiResponse(ApiResponseStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponseStatus getStatus() {
        return status;
    }
    public void setStatus(ApiResponseStatus status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}