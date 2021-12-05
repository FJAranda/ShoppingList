package com.example.shoppinglist.list;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist.R;
import com.example.shoppinglist.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private ArrayList<Item> items;
    private OnManageItemListener listener;

    public MyListAdapter(ArrayList<Item> items, OnManageItemListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public interface OnManageItemListener{
        void onEditItem(Item item);
        void onDeleteItem(Item item);
    }

    public void update(List<Item> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNombre.setText(items.get(position).getNombre());
        holder.tvCantidad.setText(String.valueOf(items.get(position).getCantidad()));
        holder.bind(items.get(position), listener);
    }

    @Override
    public int getItemCount() {
        Log.d("ItemsSize", String.valueOf(items.size()));
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvCantidad;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreItem);
            tvCantidad = itemView.findViewById(R.id.tvCantidadItem);
        }

        public void bind(Item item, OnManageItemListener listener){
            itemView.setOnClickListener(v ->{
                listener.onEditItem(item);
            });
            itemView.setOnLongClickListener(v ->{
                listener.onDeleteItem(item);
                return true;
            });
        }
    }
}
