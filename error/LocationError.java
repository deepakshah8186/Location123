package com.example.demo.error;

public enum LocationError {
    LOCATION_NOT_FOUND("location_not_found", "location code does not exist in Location");


    private String code;
    private String message;

    LocationError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
