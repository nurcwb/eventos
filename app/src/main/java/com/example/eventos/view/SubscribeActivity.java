package com.example.eventos.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.eventos.R;
import com.example.eventos.databinding.ActivitySubscribeBinding;
import com.example.eventos.model.StatusCallService;
import com.example.eventos.viewmodel.SubscriveViewModel;

public class SubscribeActivity extends BaseActivity {
    private SubscriveViewModel viewModel;
    private ActivitySubscribeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_subscribe);
        viewModel = ViewModelProviders.of(this).get(SubscriveViewModel.class);
        binding.btnSubscribe.setOnClickListener(v -> {
            viewModel.subscriveOnEvente(binding.tieName.getText().toString(), binding.tieEmail.getText().toString());
        });

        viewModel.liveData.observe(this, new Observer<StatusCallService>() {
            @Override
            public void onChanged(StatusCallService statusCallService) {
                if (statusCallService.getSuccessful()) {
                    showMessage(getString(R.string.subscribe_title),statusCallService.getMessage());
                    binding.tieName.setText("");
                    binding.tieEmail.setText("");
                }
            }
        });

    }


}