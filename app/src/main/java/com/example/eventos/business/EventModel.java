package com.example.eventos.business;

import com.example.eventos.data.EventRepository;
import com.example.eventos.data.IEventRepository;
import com.example.eventos.data.request.SubscribeEventRequest;
import com.example.eventos.data.response.EventListResponse;
import com.example.eventos.data.response.EventResponse;
import com.example.eventos.data.response.SubscribeReponse;
import com.example.eventos.model.Event;
import com.example.eventos.model.StatusCallService;
import com.example.eventos.vo.EventItemListVO;
import com.example.eventos.vo.EventVO;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class EventModel implements IEventModel {
    private static IEventRepository repository;
    private static List<Event> eventList;
    private static Event event;

    private static IEventModel instance;

    public static IEventModel getInstance() {
        if (null == instance) {
            instance = new EventModel();
            repository = new EventRepository();
        }
        return instance;
    }

    @Override
    public Observable<StatusCallService> requestEventList() {
        return repository.getListEvent().map(new Function<EventListResponse, StatusCallService>() {
            @Override
            public StatusCallService apply(@NonNull EventListResponse eventListResponse) throws Exception {
                if (eventListResponse.getIsSuccessful()) {
                    eventList = eventListResponse.getEventList();
                }
                return new StatusCallService(eventListResponse.getIsSuccessful(), eventListResponse.getMessage());
            }
        });
    }

    @Override
    public List<EventItemListVO> getEventItemListVOS() {
        List<EventItemListVO> eventItemListVO = new ArrayList<>();
        for (Event event : eventList) {
            eventItemListVO.add(new EventItemListVO(event.getDate(), event.getTitle(), event.getId()));
        }
        return eventItemListVO;
    }

    @Override
    public Observable<StatusCallService> requestEventVOById(String id) {
        return repository.getEventById(id).map(new Function<EventResponse, StatusCallService>() {
            @Override
            public StatusCallService apply(@NonNull EventResponse eventResponse) throws Exception {
                StatusCallService statusCallService = new StatusCallService(eventResponse.getSuccessful(), eventResponse.getMessage());
                if (eventResponse.getSuccessful()) {
                    event = eventResponse.getEvent();
                }
                return statusCallService;
            }
        });
    }

    @Override
    public EventVO getEventVO() {
        EventVO eventVO = new EventVO(event.getDate(), event.getTitle(), event.getId(), event.getPeople(), event.getDescription(), event.getImage(), event.getPrice());
        return eventVO;
    }

    @Override
    public Observable<StatusCallService> subscriveOnEvente(SubscribeEventRequest eventRequest) {
        eventRequest.setEventId(event.getId());

        return repository.subscriveOnEvente(eventRequest).map(new Function<SubscribeReponse, StatusCallService>() {
            @Override
            public StatusCallService apply(@NonNull SubscribeReponse subscribeReponse) throws Exception {
                return new StatusCallService(subscribeReponse.getSuccessful(), subscribeReponse.getMessage());
            }
        });
    }
}
