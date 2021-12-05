package com.example.shoppinglist.item;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppinglist.Contract;
import com.example.shoppinglist.list.ListPresenter;
import com.example.shoppinglist.databinding.FragmentItemBinding;
import com.example.shoppinglist.model.Item;
import com.google.android.material.snackbar.Snackbar;

public class ItemFragment extends Fragment implements Contract.View {
    FragmentItemBinding binding;
    Contract.ItemPresenter presenter;

    int behaviour;
    Item temp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        behaviour = getArguments().getInt("behaviour");
        temp = (Item) getArguments().getSerializable("item");
        presenter = new ListPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentItemBinding.inflate(inflater);
        binding.btnOk.setOnClickListener(v->{
            //Add
            if (behaviour == 0){
                Item item = new Item(binding.etNombre.getText().toString(), Integer.parseInt(binding.etCantidad.getText().toString()));
                presenter.add(item);
            }//Edit
            else if (behaviour == 1){
                if (temp != null) {
                    Log.d("Modify", "Modify OK");
                    Item item = new Item(binding.etNombre.getText().toString(), Integer.parseInt(binding.etCantidad.getText().toString()));
                    presenter.modify(temp, item);
                }
            }
        });
        if (behaviour == 1) {
            binding.etNombre.setText(temp.getNombre());
            binding.etCantidad.setText(String.valueOf(temp.getCantidad()));
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showMessage(Item item) {
        Log.d("View", "ShowMessage");
        if (behaviour == 0) {
            Snackbar.make(getView(), "Añadido el item "+item.getNombre(), Snackbar.LENGTH_SHORT).show();
        }else if(behaviour == 1){
            Snackbar.make(getView(), "Modificado el item "+item.getNombre(), Snackbar.LENGTH_SHORT).show();
        }
    }
}