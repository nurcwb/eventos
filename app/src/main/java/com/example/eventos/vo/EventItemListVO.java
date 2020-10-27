package com.example.eventos.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EventItemListVO {
    String eventData;
    String eventTitle;
    String eventId;

    public EventItemListVO(Integer eventData, String eventTitle, String eventId) {
        Date date = new Date(eventData);
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        this.eventData = DateFor.format(date);

        this.eventTitle = eventTitle;
        this.eventId = eventId;
    }

    public String getEventData() {
        return eventData;
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
