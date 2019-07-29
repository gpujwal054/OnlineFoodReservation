package com.example.smartrestro;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import khalti.checkOut.api.Config;
import khalti.checkOut.api.OnCheckOutListener;
import khalti.checkOut.helper.KhaltiCheckOut;
import khalti.widget.KhaltiButton;


public class CartFragment extends Fragment implements View.OnClickListener{
    KhaltiButton khaltiButton;
    Config config = new Config("test_public_key_f4550a60cfb14063b1ecbad7598a681f", "1", "abc", "abc.com", (long)1000, new OnCheckOutListener() {

        @Override
        public void onSuccess(HashMap<String, Object> data) {
            Log.i("Payment confirmed", data+"");
        }

        @Override
        public void onError(String action, String message) {
            Log.i(action, message);
        }
    });

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        KhaltiButton khaltiButton = (KhaltiButton) getView().findViewById(R.id.khalti_button);
//
        View v = inflater.inflate(R.layout.fragment_cart, container, false);
        khaltiButton = v.findViewById(R.id.khalti_button);
        khaltiButton.setOnClickListener(CartFragment.this);
        return v;
    }


    @Override
    public void onClick(View view) {
        KhaltiCheckOut khaltiCheckOut = new KhaltiCheckOut(getContext(), config);
        khaltiCheckOut.show();
    }
}
