package com.example.eventos.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eventos.business.EventModel;
import com.example.eventos.business.IEventModel;
import com.example.eventos.model.StatusCallService;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivityviewModel extends ViewModel {
    private IEventModel viewModel = EventModel.getInstance();
    private CompositeDisposable disposable = new CompositeDisposable();

    public MutableLiveData<StatusCallService> liveData = new MutableLiveData<>();

    public void requestEventList() {
        disposable.add(viewModel.requestEventList().subscribe(statusCallService ->
                liveData.postValue(statusCallService)));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
