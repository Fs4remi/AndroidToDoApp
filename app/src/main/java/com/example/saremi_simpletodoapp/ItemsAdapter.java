package com.example.saremi_simpletodoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{
    public interface onLongClickListener{
        void onItemLongClicked(int position);
    }

    List<String> items;
    onLongClickListener longClick;

    //parameter: the data about the model
    public ItemsAdapter(List<String> data, onLongClickListener l){
        this.items= data;
        longClick = l;
    }
    @NonNull
    @Override
    //CREATES EACH VIEW
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //use layout inflator to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        //wrap the return thing inside a view holder and return it
        return new ViewHolder(todoView);
    }

    @Override
    // takes data at position and puts it in a viewholder obj
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Grab the data sitting in the position
        //Bind the data into the holder that's given in the parameters
        holder.bind(items.get(position));
    }

    @Override
    //knows the number of items available in the data
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textone = itemView.findViewById(android.R.id.text1);
        }

        public void bind(String item){
            textone.setText(item);
            textone.setOnLongClickListener( new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    longClick.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
