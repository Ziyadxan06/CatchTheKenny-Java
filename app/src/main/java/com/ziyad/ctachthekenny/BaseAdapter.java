package com.ziyad.ctachthekenny;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ziyad.ctachthekenny.databinding.RecyclerRowBinding;

import java.util.ArrayList;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.BaseHolder> {

    ArrayList<Scoredata> scoredataArrayList;

    public BaseAdapter(ArrayList<Scoredata> scoredataArrayList) {
        this.scoredataArrayList = scoredataArrayList;
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BaseHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
        holder.binding.recyclerViewTextView.setText(scoredataArrayList.get(position).name + ": " + scoredataArrayList.get(position).score);
    }

    @Override
    public int getItemCount() {
        return scoredataArrayList.size();
    }

    public class BaseHolder extends RecyclerView.ViewHolder{

        private RecyclerRowBinding binding;

        public BaseHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
