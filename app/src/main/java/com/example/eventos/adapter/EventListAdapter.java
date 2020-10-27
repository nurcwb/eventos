package com.example.eventos.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventos.vo.EventItemListVO;
import com.example.eventos.databinding.EventListItemLayoutBinding;
import com.example.eventos.viewmodel.ListEventViewModel;

import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventViewHolder> {
    private List<EventItemListVO> listVOS;
    private ListEventViewModel model;

    public EventListAdapter(List<EventItemListVO> listVOS, ListEventViewModel model) {
        this.listVOS = listVOS;
        this.model = model;
    }


    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        EventListItemLayoutBinding binding = EventListItemLayoutBinding.inflate(layoutInflater, parent, false);
        return new EventViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.bind(listVOS.get(position), model);
    }

    @Override
    public int getItemCount() {
        return listVOS.size();
    }
}
