package com.example.smartrestro;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends Fragment implements View.OnClickListener{
    ImageButton btnBreakFast, btnPizza, btnBurger;
    public FoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_food, container, false);
        btnBreakFast = v.findViewById(R.id.imgBtnBreakfast);
        btnPizza = v.findViewById(R.id.imgBtnPizza);
        btnBurger = v.findViewById(R.id.imgBtnBurger);
        return v;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()){
            case R.id.imgBtnBreakfast:
                fragment = new BreakFastFragment();
                replaceFragment(fragment);
                break;
            case R.id.imgBtnPizza:
                fragment = new PizzaFragment();
                replaceFragment(fragment);
                break;
            case R.id.imgBtnBurger:
                fragment = new BurgerFragment();
                replaceFragment(fragment);
                break;
        }
    }
    public void replaceFragment(Fragment someFragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
