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
public class MenuFragment extends Fragment implements View.OnClickListener{
    Button btnFood,btnDessert,btnDrink,btnBar;

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
        View view = inflater.inflate(R.layout.fragment_table, container, false);
        btnFood = view.findViewById(R.id.btnFoodFragment);
        btnDessert = view.findViewById(R.id.btnDessertFragment);
        btnDrink = view.findViewById(R.id.btnDrinkFragment);
        btnBar = view.findViewById(R.id.btnBarFragment);
        btnFood.setOnClickListener(this);
        btnDessert.setOnClickListener(this);
        btnDrink.setOnClickListener(this);
        btnBar.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()){
            case R.id.btnFoodFragment:
                fragment = new FoodFragment();
                replaceFragment(fragment);
                break;
            case R.id.btnDessertFragment:
                fragment = new DessertFragment();
                replaceFragment(fragment);
                break;
            case R.id.btnDrinkFragment:
                fragment = new DrinksFragment();
                replaceFragment(fragment);
                break;
            case R.id.btnBarFragment:
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
