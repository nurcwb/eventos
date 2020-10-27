package com.example.eventos.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.eventos.R;
import com.example.eventos.databinding.ActivityEventBinding;
import com.example.eventos.viewmodel.EventViewModel;

public class EventActivity extends AppCompatActivity {
    private ActivityEventBinding binding;
    private EventViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_event);
        model = ViewModelProviders.of(this).get(EventViewModel.class);
        Glide.with(this).load(model.getEvent().getEventImageURL()).into(binding.ivImageEvent);
        binding.setModel(model.getEvent());

        binding.btnShare.setOnClickListener(v ->{
            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("text/plain");
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

            share.putExtra(Intent.EXTRA_SUBJECT,
                    model.getEvent().getEventTitle());
            share.putExtra(Intent.EXTRA_TEXT,
                    getString(R.string.title_invite_to_event));

            startActivity(Intent.createChooser(share, getString(R.string.share)));
        });

        binding.btnSubscribe.setOnClickListener(v -> startActivity(new Intent(this, SubscribeActivity.class)));
    }
}