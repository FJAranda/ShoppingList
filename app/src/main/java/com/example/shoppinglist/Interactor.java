package com.example.shoppinglist;

import com.example.shoppinglist.model.Item;
import com.example.shoppinglist.model.ItemRepository;

import java.util.List;

public class Interactor implements Contract.OnRepositoryCallback {
    Contract.OnInteractorListener listener;

    public Interactor(Contract.OnInteractorListener listener){
        this.listener = listener;
    }

    //Metodos de respuesta del repositorio
    @Override
    public void onSuccessList(List<Item> items) {
        listener.onSuccess(items);
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onSuccess(Item item) {
        listener.onSuccess(item);
    }

    @Override
    public void onFailure(Item item) {

    }

    @Override
    public void onDeleteSuccess(Item item) {
        listener.onDeleteSuccess("Eliminado el item " + item.getNombre());
    }

    //Metodos del interactor
    public void load(){
        ItemRepository.getInstance(this).getList();
    }

    public void add(Item item){
        ItemRepository.getInstance(this).add(item);
    }

    public void modify(Item oldItem, Item item){
        ItemRepository.getInstance(this).modify(oldItem, item);
    }

    public void delete(Item item) {
        ItemRepository.getInstance(this).delete(item);
    }
}
