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
public class TableFragment extends Fragment implements View.OnClickListener {
    Button btnFirst,btnSecond,btnBalcony,btnGarden;
    public TableFragment() {
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
        btnFirst = view.findViewById(R.id.btnFirstFloor);
        btnSecond = view.findViewById(R.id.btnSecondFloor);
        btnBalcony = view.findViewById(R.id.btnBalconyFloor);
        btnGarden = view.findViewById(R.id.btnGardenFloor);
        btnFirst.setOnClickListener(this);
        btnSecond.setOnClickListener(this);
        btnBalcony.setOnClickListener(this);
        btnGarden.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()){
            case R.id.btnFirstFloor:
                fragment = new FirstFloorFragment();
                replaceFragment(fragment);
                break;
            case R.id.btnSecondFloor:
                fragment = new SecondFloorFragment();
                replaceFragment(fragment);
                break;
            case R.id.btnBalconyFloor:
                fragment = new BalconyFloorFragment();
                replaceFragment(fragment);
                break;
            case R.id.btnGardenFloor:
                fragment = new GardenFloorFragment();
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
