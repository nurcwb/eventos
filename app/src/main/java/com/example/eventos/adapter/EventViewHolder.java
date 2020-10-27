package com.example.eventos.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventos.vo.EventItemListVO;
import com.example.eventos.databinding.EventListItemLayoutBinding;
import com.example.eventos.viewmodel.ListEventViewModel;

public class EventViewHolder extends RecyclerView.ViewHolder {
    private EventListItemLayoutBinding binding;
    private Context context;

    public EventViewHolder(@NonNull EventListItemLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(EventItemListVO itemListVO, ListEventViewModel model) {
        binding.setEvent(itemListVO);
        binding.executePendingBindings();
        binding.cvItem.setOnClickListener(v -> {
            model.requestEventVOById(itemListVO.getEventId());
        });
    }
}
