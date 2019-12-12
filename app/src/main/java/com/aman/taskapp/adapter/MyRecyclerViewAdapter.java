package com.aman.taskapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aman.taskapp.R;
import com.aman.taskapp.RecyclerViewClickListener;
import com.aman.taskapp.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private ArrayList<Product> productList;
    private RecyclerViewClickListener mListener;

    public MyRecyclerViewAdapter(ArrayList<Product> productList, RecyclerViewClickListener mListener) {
        this.productList = productList;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new MyViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        setImage(holder.image, productList.get(position).getProductImage());
        holder.txtId.setText(productList.get(position).getProductId().toString());
        holder.txtName.setText(productList.get(position).getProductName());
        holder.txtDesc.setText(productList.get(position).getProductDesc());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    private void setImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView txtId, txtName, txtDesc;
        private RecyclerViewClickListener recyclerViewClickListener;

        MyViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            image = itemView.findViewById(R.id.row_imageView);
            txtId = itemView.findViewById(R.id.row_text_view_id);
            txtName = itemView.findViewById(R.id.row_text_view_name);
            txtDesc = itemView.findViewById(R.id.row_text_view_desc);
            recyclerViewClickListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recyclerViewClickListener.onClick(v, getAdapterPosition());
        }
    }
}
