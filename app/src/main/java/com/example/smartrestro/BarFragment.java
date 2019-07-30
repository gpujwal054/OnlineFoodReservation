package com.example.smartrestro;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class BarFragment extends Fragment {
    Button cart;
    EditText name,quantity,price;
    DatabaseReference databaseCart;
    public BarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bar, container, false);
        name = v.findViewById(R.id.prodName);
        quantity = v.findViewById(R.id.prodQuantity);
        price = v.findViewById(R.id.prodPrice);
        databaseCart = FirebaseDatabase.getInstance().getReference("cart");
        cart = v.findViewById(R.id.btnAddCart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCart();
            }
        });
        return v;
    }

    private void addCart(){
        String productName = name.getText().toString().trim();
        String productQuantity = quantity.getText().toString();
        String productPrice = price.getText().toString();

        if (!TextUtils.isEmpty(productName)){
            String id = databaseCart.push().getKey();
            Cart cart = new Cart(id,productName,productQuantity,productPrice);
            databaseCart.child(id).setValue(cart);
            Toast.makeText(getActivity(),"Added to cart",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getActivity(),"You should enter name",Toast.LENGTH_LONG).show();
        }
    }

}
