package com.example.smartrestro;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrinksFragment extends Fragment {
    private DatabaseReference mReference;
    private ChildEventListener mChildEventListener;
    TextView itemNameView,itemDescriptionView,itemPriceView,itemIdVIew,imageUrl;
    private ListView listView;
    private ItemAdapter adapter;

    public DrinksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_drinks, container, false);
        listView = v.findViewById(R.id.items_list);
        mReference = FirebaseDatabase.getInstance().getReference("/item/");
        List<Item> items = new ArrayList<>();
        adapter = new ItemAdapter(getActivity(), items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemNameView = view.findViewById(R.id.item_name_view);
                itemDescriptionView = view.findViewById(R.id.item_description_view);
                itemPriceView = view.findViewById(R.id.item_price_view);
                itemIdVIew = view.findViewById(R.id.invisible_item_id_view);
                imageUrl = view.findViewById(R.id.invisible_item_image_url);
            }
        });
        return v;
    }

    private void attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Item item = dataSnapshot.getValue(Item.class);
                    item.itemId = dataSnapshot.getKey();
                    adapter.add(item);

                }

                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    //reattaching updates list in UI
                    adapter.clear();
                    detachDatabaseReadListener();
                    attachDatabaseReadListener();
                }

                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    // reattaching the listener updates our list in UI
                    adapter.clear();
                    detachDatabaseReadListener();
                    attachDatabaseReadListener();
                }

                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                public void onCancelled(DatabaseError databaseError) {
                }
            };
            mReference.addChildEventListener(mChildEventListener);
        }
    }

    private void detachDatabaseReadListener() {
        if (mChildEventListener != null) {
            mReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
    }

    @Override
    public void onPause() {
        adapter.clear();
        detachDatabaseReadListener();
        super.onPause();
    }

    @Override
    public void onStart() {
        attachDatabaseReadListener();
        super.onStart();
    }

}
