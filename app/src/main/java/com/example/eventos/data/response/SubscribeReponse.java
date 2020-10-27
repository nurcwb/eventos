package com.example.eventos.data.response;

public class SubscribeReponse {
    Boolean isSuccessful;
    String message;
    Boolean subscribeStatus;

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(Boolean successful) {
        isSuccessful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSubscribeStatus() {
        return subscribeStatus;
    }

    public void setSubscribeStatus(Boolean subscribeStatus) {
        this.subscribeStatus = subscribeStatus;
    }
}
