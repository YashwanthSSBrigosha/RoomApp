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
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onEditClicked(int position);
        void onDeleteClicked(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

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

        ListViewHolder listViewHolder = new ListViewHolder(view,mListener);

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

    public static class ListViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        Button btnEdit,btnDelete;
        View rootView;

        public ListViewHolder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);
            rootView = itemView;
            textView = itemView.findViewById(R.id.textView);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onEditClicked(position);
                        }
                    }
                }
            });
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onDeleteClicked(position);
                        }
                    }
                }
            });

        }
    }
}
