package com.aman.taskapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aman.taskapp.R;
import com.aman.taskapp.RecyclerViewClickListener;
import com.aman.taskapp.adapter.MyRecyclerViewAdapter;
import com.aman.taskapp.model.Product;
import com.aman.taskapp.network.API;
import com.aman.taskapp.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListActivity extends AppCompatActivity {

    public static final String KEY_INTENT_PRODUCT = "key_product";

    private RecyclerView recyclerView;
    private ApiInterface apiInterface;
    private Retrofit retrofit;
    private ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        String username = getSharedPreferences(LoginActivity.PREF_FILENAME, MODE_PRIVATE)
                .getString(LoginActivity.KEY_USERNAME, "Null");
        setTitle("Hi " + username);

        recyclerView = findViewById(R.id.list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        products = new ArrayList<>();

        retrofit = API.getRetrofitInstance();
        apiInterface = retrofit.create(ApiInterface.class);

        Call<List<Product>> call = apiInterface.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                RecyclerViewClickListener recyclerViewClickListener = (view, position) -> {
                    Product product = products.get(position);
                    Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
                    intent.putExtra(KEY_INTENT_PRODUCT, product);
                    startActivity(intent);
                };

                products = (ArrayList<Product>) response.body();
                MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(products, recyclerViewClickListener);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ListActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
