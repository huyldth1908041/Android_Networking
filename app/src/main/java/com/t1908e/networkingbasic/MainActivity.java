package com.t1908e.networkingbasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.t1908e.networkingbasic.adapter.ArticleAdapter;
import com.t1908e.networkingbasic.model.Item;
import com.t1908e.networkingbasic.service.ArticleService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    //static component
    private RecyclerView recyclerViewListArticle;
    private List<Item> listItem = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchData();
        recyclerViewListArticle = findViewById(R.id.recycleViewArticle);

        Log.d("Main", "list size: " + String.valueOf(listItem.size()));
        ArticleAdapter articleAdapter = new ArticleAdapter(this, listItem);
        recyclerViewListArticle.setAdapter(articleAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerViewListArticle.setLayoutManager(layoutManager);
    }

    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ArticleService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ArticleService service = retrofit.create(ArticleService.class);
        service.getListData().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(response.body() == null) {
                    return;
                }
                List<Item> list = response.body();
                Log.d("Main", String.valueOf(list.size()));
                listItem.addAll(list);
                Log.d("Main", "list size in call back: " + String.valueOf(listItem.size()));
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.d("Main", "Fetch failed: " + t);
            }
        });
    }
}