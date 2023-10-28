package com.kapture.elk.pro.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Response implements Serializable {

    public static String SUCCESS_CODE = "success";

    public static String FAIL_CODE = "error";

    Object data;

    List<Object> dataList;

    boolean isSuccess;

    String statusCode;

    String errorReason;

    int size;

    public static Response getFailedResponse() {
        Response response = new Response();
        response.setStatusCode(Response.FAIL_CODE);
        response.setSuccess(false);
        return response;
    }

    public static Response getFailedResponse(String errorReason) {
        Response response = new Response();
        response.setStatusCode(Response.FAIL_CODE);
        response.setErrorReason(errorReason);
        response.setSuccess(false);
        return response;
    }


    public static Response getSuccessResponse() {
        Response esSearchResponse = new Response();
        esSearchResponse.setStatusCode(Response.SUCCESS_CODE);
        esSearchResponse.setSuccess(true);
        return esSearchResponse;
    }

}
