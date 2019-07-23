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
public class FoodFragment extends Fragment implements View.OnClickListener {
    Button breakfast,pizza,burger;
    public FoodFragment() {
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
        View v = inflater.inflate(R.layout.fragment_food, container, false);
        breakfast = v.findViewById(R.id.btnBreakFast);
        breakfast.setOnClickListener(this);
        pizza = v.findViewById(R.id.btnPizza);
        pizza.setOnClickListener(this);
        burger = v.findViewById(R.id.btnBurger);
        burger.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()){
            case R.id.btnBreakFast:
                fragment = new BreakFastFragment();
                replaceFragment(fragment);
                break;
            case R.id.btnPizza:
                fragment = new PizzaFragment();
                replaceFragment(fragment);
                break;
            case R.id.btnBurger:
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
