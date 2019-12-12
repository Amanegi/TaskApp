package com.aman.taskapp.network;

import com.aman.taskapp.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("http://www.mocky.io/v2/5d15fc1b0e00003311a1167a")
    Call<List<Product>> getProducts();

}
