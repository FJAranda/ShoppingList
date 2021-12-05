package com.example.shoppinglist.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglist.Contract;
import com.example.shoppinglist.R;
import com.example.shoppinglist.databinding.FragmentListBinding;
import com.example.shoppinglist.model.Item;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment implements Contract.ListView, MyListAdapter.OnManageItemListener {
    FragmentListBinding binding;
    private MyListAdapter adapter;
    private Contract.ListPresenter listPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listPresenter = new ListPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentListBinding.inflate(inflater);
        binding.floatingActionButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("behaviour", 0);
            Navigation.findNavController(getView()).navigate(R.id.action_listFragment_to_itemFragment, bundle);
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRV();
    }

    private void initRV() {
        adapter = new MyListAdapter(new ArrayList<>(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        binding.rvList.setLayoutManager(linearLayoutManager);
        binding.rvList.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        listPresenter.load();
    }

    @Override
    public void onResume() {
        super.onResume();
        listPresenter.load();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        listPresenter =null;
    }

    @Override
    public void showData(List<Item> items) {
        Log.d("ShowData", "Ejecutando view.showData");
        adapter.update(items);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
        listPresenter.load();
    }

    @Override
    public void onEditItem(Item item) {
        Log.d("Edit item", item.getNombre());
        Bundle bundle = new Bundle();
        bundle.putInt("behaviour", 1);
        bundle.putSerializable("item", item);
        Navigation.findNavController(getView()).navigate(R.id.action_listFragment_to_itemFragment, bundle);
    }

    @Override
    public void onDeleteItem(Item item) {
        Log.d("Delete item", item.getNombre());
        listPresenter.delete(item);
    }
}