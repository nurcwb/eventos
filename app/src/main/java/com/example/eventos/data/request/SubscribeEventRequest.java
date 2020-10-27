package com.example.eventos.data.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubscribeEventRequest {

    @SerializedName("eventId")
    @Expose
    private String eventId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
