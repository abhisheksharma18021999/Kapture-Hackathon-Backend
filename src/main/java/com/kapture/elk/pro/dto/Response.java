package com.kapture.elk.pro.dto;


import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class Response<T extends BaseObject> implements Serializable {

    public static String SUCCESS_CODE = "success";

    public static String FAIL_CODE = "error";

    Object additionalInfo;

    List<T> resultList;

    boolean isSuccess;

    String statusCode;

    String errorReason;

    int size;

    public static Response<BaseObject> getFailedResponse() {
        Response<BaseObject> response = new Response<>();
        response.setStatusCode(Response.FAIL_CODE);
        response.setSuccess(false);
        return response;
    }

    public static Response<BaseObject> getFailedResponse(String errorReason) {
        Response<BaseObject> response = new Response<>();
        response.setStatusCode(Response.FAIL_CODE);
        response.setErrorReason(errorReason);
        response.setSuccess(false);
        return response;
    }

    public static Response<BaseObject> getFailedResponse(Response<BaseObject> esSearchResponse, String errorReason) {
        if (esSearchResponse == null) {
            esSearchResponse = getFailedResponse();
        }
        esSearchResponse.setStatusCode(Response.FAIL_CODE);
        esSearchResponse.setErrorReason(errorReason);
        esSearchResponse.setSuccess(false);
        return esSearchResponse;
    }

    public static Response<BaseObject> getSuccessResponse() {
        Response<BaseObject> esSearchResponse = new Response<>();
        esSearchResponse.setStatusCode(Response.SUCCESS_CODE);
        esSearchResponse.setSuccess(true);
        return esSearchResponse;
    }

}
