package com.example.eventos.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eventos.R;
import com.example.eventos.adapter.EventListAdapter;
import com.example.eventos.databinding.ActivityListEventsBinding;
import com.example.eventos.model.StatusCallService;
import com.example.eventos.viewmodel.ListEventViewModel;

public class ListEventsActivity extends BaseActivity {
    private ActivityListEventsBinding binding;
    private ListEventViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_events);
        model = ViewModelProviders.of(this).get(ListEventViewModel.class);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvEventListTime.setLayoutManager(layoutManager);
        EventListAdapter adapter = new EventListAdapter(model.getEventListItem(), model);
        binding.rvEventListTime.setAdapter(adapter);

        model.liveData.observe(this, new Observer<StatusCallService>() {
            @Override
            public void onChanged(StatusCallService statusCallService) {
                if (statusCallService.getSuccessful()) {
                    startActivity(new Intent(ListEventsActivity.this, EventActivity.class));
                } else {
                    showMessage(getString(R.string.info_about_event),statusCallService.getMessage());
                }
            }
        });
    }


}