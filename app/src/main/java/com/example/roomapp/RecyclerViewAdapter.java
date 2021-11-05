package com.example.roomapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ListViewHolder> {

    private Context context;
    private List<Names> names;

    public RecyclerViewAdapter(Context context){
        this.context = context;
    }

    public void setNames(List<Names> names){
        this.names = names;
        notifyDataSetChanged();
    }

    public RecyclerViewAdapter(List<Names> name){
        this.names = name;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.listitem_layout,parent,false);

        ListViewHolder listViewHolder = new ListViewHolder(view);

        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

        holder.textView.setText(this.names.get(position).name);
    }

    @Override
    public int getItemCount() {
        return this.names.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        Button btnEdit,btnDelete;
        View rootView;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            textView = itemView.findViewById(R.id.textView);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);

        }
    }
}
