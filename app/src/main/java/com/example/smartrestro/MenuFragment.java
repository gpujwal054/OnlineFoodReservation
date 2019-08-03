package com.example.smartrestro;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment  {
    RecyclerView recyclerView;
    TextView name,price,image;
    List<Menu> list;
    private MenuFragment adapter;
    private DatabaseReference myRef;
    FirebaseRecyclerAdapter firebaseRecyclerAdapter;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_menu, container, false);
        //recyclerView = v.findViewById(R.id.myrecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        name = v.findViewById(R.id.tvMenu);
        price = v.findViewById(R.id.tvPrice);
        image = v.findViewById(R.id.imgMenu);
        myRef= FirebaseDatabase.getInstance().getReference().child("/menu");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    if (dataSnapshot.hasChild("productName"))
                    {
                        String string = dataSnapshot.child("productName").getValue().toString();
                        name.setText(string);
                    }
                    if(dataSnapshot.hasChild("productPrice"))
                    {
                        String string = dataSnapshot.child("productPrice").getValue().toString();
                        price.setText(string);

                    }
                    if(dataSnapshot.hasChild("productImage"))
                    {
                        String string = dataSnapshot.child("productImage").getValue().toString();


                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        recyclerView.setHasFixedSize(true);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout root;
        public TextView menu,price;
        public ImageView img;

        public ViewHolder(View itemView){
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            menu = itemView.findViewById(R.id.tvMenu);
            price = itemView.findViewById(R.id.tvPrice);
        }

        public void setMenu(String string){
            menu.setText(string);
        }

        public void setPrice(String string){
            price.setText(string);
        }

        public void setImg(Context c,String string){
            img = itemView.findViewById(R.id.imgMenu);
        }
    }
}
