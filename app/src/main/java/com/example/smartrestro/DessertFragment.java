package com.example.smartrestro;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DessertFragment extends Fragment {
    ImageButton imageCake,imagePudding,imageBrownie;
    Button incr1,incr2,incr,dcr1,dcr2,dcr;
    TextView txt1,txt2,txt3,txt4,txt5,txt;
    EditText edt1,edt2,edt3;


    public DessertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dessert, container, false);
        imageCake = v.findViewById(R.id.imgBtnCake);
        imagePudding = v.findViewById(R.id.imgBtnPudding);
        imageBrownie = v.findViewById(R.id.imgBtnBrownie);
        incr1 = v.findViewById(R.id.btnIncrement1);
        incr2 = v.findViewById(R.id.btnIncrement2);
        incr = v.findViewById(R.id.btnIncrement);
        dcr1 = v.findViewById(R.id.btnDecrement1);
        dcr2 = v.findViewById(R.id.btnDecrement2);
        dcr = v.findViewById(R.id.btnDecrement);
        txt1 = v.findViewById(R.id.tVcake);
        txt2 = v.findViewById(R.id.tVprice);
        txt3 = v.findViewById(R.id.tVPudding);
        txt4 = v.findViewById(R.id.tVPrice);
        txt5 = v.findViewById(R.id.tVBrownie);
        txt = v.findViewById(R.id.tVBrownie1);
        edt1 = v.findViewById(R.id.textView1);
        edt2 = v.findViewById(R.id.textView2);
        edt3 = v.findViewById(R.id.textView);
        return v;
    }

}
