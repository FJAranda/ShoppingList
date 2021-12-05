package com.example.shoppinglist.list;

import com.example.shoppinglist.Contract;
import com.example.shoppinglist.Interactor;
import com.example.shoppinglist.model.Item;

import java.util.List;

public class ListPresenter implements Contract.ListPresenter, Contract.ItemPresenter, Contract.OnInteractorListener {
    private Contract.ListView listView;
    private Contract.View view;
    private Interactor interactor;

    public ListPresenter(Contract.ListView listView) {
        this.listView = listView;
        this.interactor = new Interactor(this);
    }

    public ListPresenter(Contract.View view) {
        this.view = view;
        this.interactor = new Interactor(this);
    }

    @Override
    public void load() {
        interactor.load();
    }

    @Override
    public void add(Item item) {
        interactor.add(item);
    }

    @Override
    public void modify(Item item, Item newItem) {
        interactor.modify(item, newItem);
    }

    @Override
    public void delete(Item item) {
        interactor.delete(item);
    }

    @Override
    public void onDestroy() {
        this.listView = null;
        this.interactor = null;
    }

    @Override
    public void onSuccess(List<Item> items) {
        listView.showData(items);
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onDeleteSuccess(String message) {
        listView.showMessage(message);
    }

    @Override
    public void onSuccess(Item item) {
        view.showMessage(item);
    }
}
