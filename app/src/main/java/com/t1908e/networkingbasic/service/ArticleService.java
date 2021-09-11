package com.t1908e.networkingbasic.service;

import com.t1908e.networkingbasic.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ArticleService {
    String API_URL = "https://api-demo-anhth.herokuapp.com";

    @GET("/data.json")
    Call<Item> getItemData();

    @GET("/datas.json")
    Call<List<Item>> getListData();
}
