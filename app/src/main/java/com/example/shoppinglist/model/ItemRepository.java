package com.example.shoppinglist.model;

import android.util.Log;

import com.example.shoppinglist.Contract;

import java.util.ArrayList;

public class ItemRepository implements Contract.Repository {
    private static ItemRepository repository;
    private static Contract.OnRepositoryCallback callback;
    private ArrayList<Item> items;

    private ItemRepository(){
        items = new ArrayList<>();
        initialice();
    }

    private void initialice() {
        items.add(new Item("Cebollas", 2));
        items.add(new Item("Calabazas", 2));
        items.add(new Item("Puerros", 2));
    }

    public static ItemRepository getInstance(Contract.OnRepositoryCallback listener){
        if (repository == null){
            repository = new ItemRepository();
        }
        repository.callback = listener;
        return repository;
    }

    @Override
    public void getList() {
        callback.onSuccessList(items);
    }

    @Override
    public void add(Item item) {
        items.add(item);
        callback.onSuccess(item);
    }

    @Override
    public void modify(Item item, Item newItem) {
        for (Item temp:items) {
            if (temp == item){
                Log.d("Repository", "Modify " + newItem.getNombre());
                temp.setNombre(newItem.getNombre());
                temp.setCantidad(newItem.getCantidad());
            }
        }
        callback.onSuccess(newItem);
    }

    @Override
    public void delete(Item item) {
        items.remove(item);
        callback.onDeleteSuccess(item);
    }
}
