package com.example.shoppinglist;

import com.example.shoppinglist.model.Item;

import java.util.List;

public interface Contract {
    interface ListView{
        void showData(List<Item> items);
        void showMessage(String message);
    }

    interface View{
        void showMessage(Item item);
    }

    interface ListPresenter {
        void load();
        void delete(Item item);
        void onDestroy();
    }

    interface OnInteractorListener{
        void onSuccess(List<Item> items);
        void onFailure(String message);
        void onDeleteSuccess(String message);
        void onSuccess(Item item);
    }

    interface Repository{
        void getList();
        void add(Item item);
        void modify(Item item, Item newItem);
        void delete(Item item);
    }

    interface OnRepositoryCallback{
        void onSuccessList(List<Item> items);
        void onFailure(String message);
        void onDeleteSuccess(Item item);
        void onSuccess(Item item);
        void onFailure(Item item);
    }

    interface ItemPresenter {
        void add(Item item);
        void modify(Item item, Item newItem);
    }
}
