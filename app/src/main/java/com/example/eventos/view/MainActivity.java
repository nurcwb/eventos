package com.example.eventos.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.eventos.R;
import com.example.eventos.databinding.ActivityMainBinding;
import com.example.eventos.model.StatusCallService;
import com.example.eventos.viewmodel.MainActivityviewModel;

public class MainActivity extends BaseActivity {
    private MainActivityviewModel model;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        model = ViewModelProviders.of(this).get(MainActivityviewModel.class);

        model.liveData.observe(this, new Observer<StatusCallService>() {
            @Override
            public void onChanged(StatusCallService statusCallService) {
                if(statusCallService.getSuccessful()){
                    startActivity(new Intent(MainActivity.this, ListEventsActivity.class));
                }else {
                    showMessage(getString(R.string.loading_list_of_events),statusCallService.getMessage());
                }
            }
        });
        binding.btnOpen.setOnClickListener(v -> model.requestEventList());
    }
}