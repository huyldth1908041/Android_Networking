package com.t1908e.networkingbasic.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.t1908e.networkingbasic.MainActivity;
import com.t1908e.networkingbasic.R;
import com.t1908e.networkingbasic.model.Item;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter {
    private Activity context;
    private List<Item> listItems;

    public ArticleAdapter(Activity context, List<Item> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // build view holder layout return về 1 view holder
        // lấy ra file artilce_item.xml trong layout
        View itemView = context.getLayoutInflater().inflate(R.layout.article_item, parent, false);
        // build 1 view holder từ file đó và return
        return new ArticleHolder(itemView);
    }

    // bind data to view holder in order to render each item
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Item currentArticle = listItems.get(position);
        ArticleHolder articleHolder = (ArticleHolder) holder;
        //load image
        Glide.with(context).load(currentArticle.getImage()).into(articleHolder.imageViewCover);
        articleHolder.txtContent.setText(currentArticle.getContent().getDescription());
        articleHolder.txtDate.setText(currentArticle.getDate());
        articleHolder.txtTitle.setText(currentArticle.getTitle());
    }

    //return size of list
    @Override
    public int getItemCount() {
        return listItems.size();
    }

    // View holder là 1 class chứa các component của 1 item ex: TextView, EditView,..
    public class ArticleHolder extends RecyclerView.ViewHolder {
        TextView txtDate;
        TextView txtTitle;
        TextView txtContent;
        ImageView imageViewCover;

        public ArticleHolder(@NonNull View itemView) {
            super(itemView);
            // gán giá trị cho các component
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            txtContent = (TextView) itemView.findViewById(R.id.txtContent);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            imageViewCover = (ImageView) itemView.findViewById(R.id.imageViewCover);

        }
    }
}
