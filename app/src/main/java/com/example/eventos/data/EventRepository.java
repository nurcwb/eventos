package com.example.eventos.data;

import com.example.eventos.data.request.SubscribeEventRequest;
import com.example.eventos.data.response.EventListResponse;
import com.example.eventos.data.response.EventResponse;
import com.example.eventos.data.response.SubscribeReponse;
import com.example.eventos.model.Event;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventRepository implements IEventRepository {
    private Retrofit retrofit;
    private IRestApiEvents apiEvents;

    public EventRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://5f5a8f24d44d640016169133.mockapi.io")
                .addConverterFactory(GsonConverterFactory.create()).build();
        apiEvents = retrofit.create(IRestApiEvents.class);
    }

    @Override
    public Observable<EventListResponse> getListEvent() {
        return Observable.create(new ObservableOnSubscribe<EventListResponse>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<EventListResponse> emitter) throws Exception {
                EventListResponse responseService = new EventListResponse();

                Call<List<Event>> listCall = apiEvents.getAllEvents();
                listCall.enqueue(new Callback<List<Event>>() {
                    @Override
                    public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                        responseService.setIsSuccessful(response.isSuccessful());
                        if (response.isSuccessful() && response.body() != null) {
                            responseService.setEventList(response.body());
                        } else {
                            responseService.setMessage(response.errorBody().toString());
                        }
                        emitter.onNext(responseService);
                        emitter.onComplete();
                    }

                    @Override
                    public void onFailure(Call<List<Event>> call, Throwable t) {
                        responseService.setIsSuccessful(false);
                        responseService.setMessage(t.getMessage());
                        emitter.onNext(responseService);
                        emitter.onComplete();
                    }
                });
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<EventResponse> getEventById(String id) {

        return Observable.create(new ObservableOnSubscribe<EventResponse>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<EventResponse> emitter) throws Exception {
                EventResponse eventResponse = new EventResponse();

                Call<Event> eventCall = apiEvents.getEventById(id);

                eventCall.enqueue(new Callback<Event>() {
                    @Override
                    public void onResponse(Call<Event> call, Response<Event> response) {
                        eventResponse.setSuccessful(response.isSuccessful());
                        if (response.isSuccessful() && response.body() != null) {
                            eventResponse.setEvent(response.body());
                        } else {
                            eventResponse.setMessage(response.errorBody().toString());
                        }
                        emitter.onNext(eventResponse);
                        emitter.onComplete();
                    }

                    @Override
                    public void onFailure(Call<Event> call, Throwable t) {
                        eventResponse.setMessage(t.getMessage());
                        emitter.onNext(eventResponse);
                        emitter.onComplete();
                    }
                });
            }
        });
    }

    @Override
    public Observable<SubscribeReponse> subscriveOnEvente(SubscribeEventRequest subscribeEvent) {
        return Observable.create(new ObservableOnSubscribe<SubscribeReponse>() {
            Call<SubscribeEventRequest> eventCall = apiEvents.posEvent(subscribeEvent);

            @Override
            public void subscribe(@NonNull ObservableEmitter<SubscribeReponse> emitter) throws Exception {
                SubscribeReponse subscribeReponse = new SubscribeReponse();
                eventCall.enqueue(new Callback<SubscribeEventRequest>() {
                    @Override
                    public void onResponse(Call<SubscribeEventRequest> call, Response<SubscribeEventRequest> response) {
                        subscribeReponse.setSuccessful(response.isSuccessful());
                        subscribeReponse.setMessage(response.message());
                        subscribeReponse.setSubscribeStatus(response.isSuccessful());
                        emitter.onNext(subscribeReponse);
                        emitter.onComplete();
                    }

                    @Override
                    public void onFailure(Call<SubscribeEventRequest> call, Throwable t) {
                        subscribeReponse.setSuccessful(false);
                        subscribeReponse.setMessage(t.getMessage());
                        subscribeReponse.setSubscribeStatus(false);
                        emitter.onNext(subscribeReponse);
                        emitter.onComplete();
                    }
                });
            }
        });
    }
}
