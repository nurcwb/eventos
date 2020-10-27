package com.example.eventos.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eventos.business.EventModel;
import com.example.eventos.business.IEventModel;
import com.example.eventos.vo.EventItemListVO;
import com.example.eventos.model.StatusCallService;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ListEventViewModel extends ViewModel {

    private IEventModel viewModel = EventModel.getInstance();
    public MutableLiveData<StatusCallService> liveData = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();

    public List<EventItemListVO> getEventListItem() {
        return viewModel.getEventItemListVOS();
    }

    public void requestEventVOById(String eventId) {
        disposable.add(viewModel.requestEventVOById(eventId).subscribe(new Consumer<StatusCallService>() {
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
