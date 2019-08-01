package com.example.smartrestro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FirebaseRecyclerAdapter<T, T1> extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        Context context;
        List<Menu> MainImageUploadInfoList;

        public RecyclerViewAdapter(Context context, List<Menu> TempList) {

            this.MainImageUploadInfoList = TempList;

            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

            ViewHolder viewHolder = new ViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            Menu menu = MainImageUploadInfoList.get(position);

            holder.menu.setText(menu.getProductName());

            holder.price.setText(menu.getProductPrice());

        }

        @Override
        public int getItemCount() {

            return MainImageUploadInfoList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public TextView menu;
            public TextView price;

            public ViewHolder(View itemView) {

                super(itemView);

                menu = (TextView) itemView.findViewById(R.id.tvMenu);

                price = (TextView) itemView.findViewById(R.id.tvPrice);
            }
        }
    }
}
