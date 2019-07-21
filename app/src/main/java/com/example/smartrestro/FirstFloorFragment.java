package com.example.smartrestro;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.net.InterfaceAddress;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFloorFragment extends Fragment {
    ImageButton imgBtnActive, imgBtnReserved, imgBtnVacant;

    public FirstFloorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first_floor, container, false);
        /*imgBtnActive = v.findViewById(R.id.btnTableActive);
        imgBtnReserved = v.findViewById(R.id.btnTableReserved);
        imgBtnVacant = v.findViewById(R.id.btnTableVacant);
        imgBtnActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MenuActivity.class);
                startActivity(intent);
            }
        });
        imgBtnReserved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MenuActivity.class);
                startActivity(intent);
            }
        });
        imgBtnVacant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MenuActivity.class);
                startActivity(intent);
            }
        });
        return v;*/
    }
}
