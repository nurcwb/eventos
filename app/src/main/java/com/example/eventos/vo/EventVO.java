package com.example.eventos.vo;

import com.example.eventos.model.Person;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventVO {
    String eventData;
    String eventTitle;
    String eventId;
    List<String> listOfPerson;
    String eventDescription;
    String eventImageURL;
    Double eventPrice;

    public EventVO(Integer eventData, String eventTitle, String eventId, List<Person> listOfPerson, String eventDescription, String eventImageURL, Double eventPrice) {
        Date date = new Date(eventData);
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        this.eventData = DateFor.format(date);
        this.eventTitle = eventTitle;
        this.eventId = eventId;

        for (Person person :
                listOfPerson) {
            this.listOfPerson = new ArrayList<>();
            this.listOfPerson.add(person.getName());
        }

        this.eventDescription = eventDescription;
        this.eventImageURL = eventImageURL;
        this.eventPrice = eventPrice;
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

    public String getListOfPerson() {
        String listPerson = new String();
        if (null != listOfPerson && listOfPerson.size() > 0) {
            for (String person :
                    listOfPerson) {
                listPerson = listPerson + ", " + person;
            }
        } else {
            listPerson = "Sem inscritos até o momento.";
        }
        return listPerson;
    }

    public void setListOfPerson(List<String> listOfPerson) {
        this.listOfPerson = listOfPerson;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventImageURL() {
        return eventImageURL;
    }

    public void setEventImageURL(String eventImageURL) {
        this.eventImageURL = eventImageURL;
    }

    public String getEventPrice() {
        return eventPrice.toString();
    }

    public void setEventPrice(Double eventPrice) {
        this.eventPrice = eventPrice;
    }
}
