package com.example.smartrestro;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends Fragment {
    ImageButton breakFastFragment,pizzaFragment,burgerFragment;

    public FoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_food, container, false);
        breakFastFragment = v.findViewById(R.id.imgBtnBreakfast);
        burgerFragment = v.findViewById(R.id.imgBtnBurger);
        pizzaFragment = v.findViewById(R.id.imgBtnPizza);
        breakFastFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),BreakFastActivity.class);
                startActivity(intent);
            }
        });
        pizzaFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PizzaActivity.class);
                startActivity(intent);
            }
        });
        burgerFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),BurgerActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

}
