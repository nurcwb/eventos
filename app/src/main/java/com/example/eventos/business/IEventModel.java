package com.example.eventos.business;

import com.example.eventos.data.request.SubscribeEventRequest;
import com.example.eventos.vo.EventItemListVO;
import com.example.eventos.vo.EventVO;
import com.example.eventos.model.StatusCallService;

import java.util.List;

import io.reactivex.Observable;

public interface IEventModel {
    Observable<StatusCallService> requestEventList();

    List<EventItemListVO> getEventItemListVOS();

    Observable<StatusCallService> requestEventVOById(String id);

    EventVO getEventVO();

    Observable<StatusCallService> subscriveOnEvente(SubscribeEventRequest eventRequest);
}
