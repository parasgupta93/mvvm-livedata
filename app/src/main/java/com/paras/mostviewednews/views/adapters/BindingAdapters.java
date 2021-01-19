package com.paras.mostviewednews.views.adapters;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.paras.mostviewednews.R;
import com.paras.mostviewednews.data.local.NewsModelLocal;
import com.paras.mostviewednews.viewmodels.NewsViewModel;

import java.util.ArrayList;

public abstract class BindingAdapters {

    @BindingAdapter({"items", "viewmodel"})
    public static void setRecyclerViewItems(RecyclerView recyclerView,
                                            ArrayList<NewsModelLocal> items, NewsViewModel viewModel){

        NewsRecAdapter newsRecAdapter = (NewsRecAdapter) recyclerView.getAdapter();
        if(newsRecAdapter == null){
            newsRecAdapter = new NewsRecAdapter(viewModel);
            recyclerView.setAdapter(newsRecAdapter);

        }

        newsRecAdapter.updateData(items != null ? items : new ArrayList<>());
    }

    @BindingAdapter("adapter")
    public static void setRecyclerViewItems(RecyclerView recyclerView,
                                            NewsRecAdapter adapter){

        recyclerView.setAdapter(adapter);

    }

    @BindingAdapter("glideImageSrc")
    public static void loadImageWithGlide(ImageView imageView, String imgSrc){

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(imageView.getContext());
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();

        Glide
                .with(imageView.getContext())
                .load(imgSrc)
                .placeholder(circularProgressDrawable)
                .centerCrop()
                .error(R.drawable.not_found)
               // .placeholder(R.drawable.background_with_top_rounded_corner)
                .into(imageView);

    }
}
