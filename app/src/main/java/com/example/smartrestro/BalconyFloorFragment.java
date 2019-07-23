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
public class BalconyFloorFragment extends Fragment implements View.OnClickListener{
    Button tab1,tab2,tab3,tab4,tab5,tab6;


    public BalconyFloorFragment() {
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
        View v = inflater.inflate(R.layout.fragment_balcony_floor, container, false);
        tab1 = v.findViewById(R.id.button1);
        tab1.setOnClickListener(this);
        tab2 = v.findViewById(R.id.button2);
        tab2.setOnClickListener(this);
        tab3 = v.findViewById(R.id.button3);
        tab3.setOnClickListener(this);
        tab4 = v.findViewById(R.id.button4);
        tab4.setOnClickListener(this);
        tab5 = v.findViewById(R.id.button5);
        tab5.setOnClickListener(this);
        tab6 = v.findViewById(R.id.button6);
        tab6.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()){
            case R.id.button1:
                fragment = new MenuFragment();
                replaceFragment(fragment);
                break;
            case R.id.button2:
                fragment = new MenuFragment();
                replaceFragment(fragment);
                break;
            case R.id.button3:
                fragment = new MenuFragment();
                replaceFragment(fragment);
                break;
            case R.id.button4:
                fragment = new MenuFragment();
                replaceFragment(fragment);
                break;
            case R.id.button5:
                fragment = new MenuFragment();
                replaceFragment(fragment);
                break;
            case R.id.button6:
                fragment = new MenuFragment();
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
