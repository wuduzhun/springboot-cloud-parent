package com.fengzhizi.util;

public class ResultEntity<T> {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAULED";
    public static final String NO_MESSAGE = "NO_MESSAGE";
    public static final String NO_DATA = "NO_DATA";
    private String result;
    private String message;
    private T data;

    public ResultEntity() {
    }

    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public static <E> ResultEntity<E> successWithoutData(){
        return new ResultEntity<E>(SUCCESS, NO_MESSAGE, null);
    }

    public static <E> ResultEntity<E> successWithData(E data){
        return new ResultEntity<E>(SUCCESS, null, data);
    }

    public static <E> ResultEntity<E> failed(String message){
        return new ResultEntity<E>(FAILED, message, null);
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static String getSUCCESS() {
        return SUCCESS;
    }

    public static String getFAILED() {
        return FAILED;
    }

    public static String getNoMessage() {
        return NO_MESSAGE;
    }

    public static String getNoData() {
        return NO_DATA;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
