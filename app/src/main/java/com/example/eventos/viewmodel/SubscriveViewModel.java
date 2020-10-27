package com.example.eventos.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eventos.business.EventModel;
import com.example.eventos.business.IEventModel;
import com.example.eventos.data.request.SubscribeEventRequest;
import com.example.eventos.model.StatusCallService;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class SubscriveViewModel extends ViewModel {
    private IEventModel viewModel = EventModel.getInstance();
    public MutableLiveData<StatusCallService> liveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    public void subscriveOnEvente(String name, String email) {
        SubscribeEventRequest eventRequest = new SubscribeEventRequest();
        eventRequest.setName(name);
        eventRequest.setEmail(email);
        disposable.add(
                viewModel.subscriveOnEvente(eventRequest).subscribe(new Consumer<StatusCallService>() {
                    @Override
                    public void accept(StatusCallService statusCallService) throws Exception {
                        liveData.postValue(statusCallService);
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
