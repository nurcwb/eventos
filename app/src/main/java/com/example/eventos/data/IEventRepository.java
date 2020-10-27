package com.example.eventos.data;

import com.example.eventos.data.request.SubscribeEventRequest;
import com.example.eventos.data.response.EventListResponse;
import com.example.eventos.data.response.EventResponse;
import com.example.eventos.data.response.SubscribeReponse;

import io.reactivex.Observable;

public interface IEventRepository {
    Observable<EventListResponse> getListEvent();

    Observable<EventResponse> getEventById(String id);

    Observable<SubscribeReponse> subscriveOnEvente(SubscribeEventRequest subscribeEvent);

}
