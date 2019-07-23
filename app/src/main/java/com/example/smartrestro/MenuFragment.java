package com.example.smartrestro;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements View.OnClickListener {
    Button foodBtn,dessertBtn,drinksBtn,barBtn;
    public MenuFragment() {
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
        View v = inflater.inflate(R.layout.fragment_menu, container, false);
        foodBtn = v.findViewById(R.id.btnFood);
        foodBtn.setOnClickListener(this);
        dessertBtn = v.findViewById(R.id.btnDessert);
        dessertBtn.setOnClickListener(this);
        drinksBtn = v.findViewById(R.id.btnDrinks);
        drinksBtn.setOnClickListener(this);
        barBtn = v.findViewById(R.id.btnBar);
        barBtn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()){
            case R.id.btnFood:
                fragment = new FoodFragment();
                replaceFragment(fragment);
                break;
            case R.id.btnDessert:
                fragment = new DessertFragment();
                replaceFragment(fragment);
                break;
            case R.id.btnDrinks:
                fragment = new DrinksFragment();
                replaceFragment(fragment);
                break;
            case R.id.btnBar:
                fragment = new BarFragment();
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
