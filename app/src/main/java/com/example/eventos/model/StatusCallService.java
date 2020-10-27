package com.example.eventos.model;

public class StatusCallService {
    Boolean isSuccessful;
    String message;

    public StatusCallService(Boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public String getMessage() {
        return message;
    }
}
