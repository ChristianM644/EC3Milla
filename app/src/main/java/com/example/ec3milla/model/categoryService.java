package com.example.ec3milla.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface categoryService
{
    @GET("list")
    Call<List<categoryEntity>> findAll();

}
