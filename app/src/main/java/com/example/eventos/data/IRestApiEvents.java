package com.example.eventos.data;

import com.example.eventos.data.request.SubscribeEventRequest;
import com.example.eventos.model.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IRestApiEvents {
    @GET("/api/events")
    public Call<List<Event>> getAllEvents();

    @GET("/api/events/{id}")
    public Call<Event> getEventById(@Path("id") String id);

    @POST("/api/checkin")
    public Call<SubscribeEventRequest> posEvent(@Body SubscribeEventRequest subscribeEvent);
}
