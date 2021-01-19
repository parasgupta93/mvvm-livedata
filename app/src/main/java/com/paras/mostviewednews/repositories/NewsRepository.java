package com.paras.mostviewednews.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.paras.mostviewednews.data.db.NewsDao;
import com.paras.mostviewednews.data.local.NewsModelLocal;
import com.paras.mostviewednews.data.pojo.MediaMetadata;
import com.paras.mostviewednews.data.pojo.Media;
import com.paras.mostviewednews.data.pojo.Post;
import com.paras.mostviewednews.data.pojo.Response;
import com.paras.mostviewednews.webservice.ApiService;

import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class NewsRepository {

    Retrofit retrofit;
    NewsDao newsDao;
   public LiveData<List<NewsModelLocal>> mListLiveData;

    @Inject
    public NewsRepository(Retrofit retrofit, NewsDao newsDao)
    {
        this.retrofit = retrofit;
        this.newsDao = newsDao;
        init();
    }

    public void insertData(List<NewsModelLocal> listLiveData ){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("Paras","inside run"+Thread.currentThread().getName());
                newsDao.clearAll();
                Log.d("Paras","After Clear");
                for (int i = 0; i < listLiveData.size(); i++) {
                    newsDao.insertNewsData(listLiveData.get(i));
                }
            }
        }).start();

    }

    private void init(){
        Log.d("Paras","init"+Thread.currentThread().getName());
        mListLiveData = newsDao.getMostViewed();
    }
    public void getMostViewedNews(MutableLiveData<ArrayList<NewsModelLocal>> dataLD){
        retrofit.create(ApiService.class).getNewsList(30, "6gSR7gqbLsxSHYnYiLsuYPBpMKTh4fTs").enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Log.d("Paras","onResponse"+Thread.currentThread().getName());
                if(response.code() == 200){

                    ArrayList<NewsModelLocal> newsData = new ArrayList<>();

                    List<Post> posts =  response.body().getPosts();
                    Log.d("Paras","onResponse Size: "+posts.size());
                    for (Post post :
                            posts) {

                        String imageTitle = "";
                        String copyrights = "";
                        MediaMetadata smallThumb = null;
                        MediaMetadata largeThumb = null;

                        if(post.getMedia() != null)
                        {
                            for (Media medium :
                                    post.getMedia()) {

                                if(medium.getType().equals("image")){

                                    imageTitle = medium.getCaption();
                                    copyrights = medium.getCopyright();

                                    if(medium.getMediaMetadata()!= null && medium.getMediaMetadata().size()>0)
                                    {
                                        smallThumb = medium.getMediaMetadata().get(0);
                                        largeThumb = medium.getMediaMetadata().get(medium.getMediaMetadata().size() -1);
                                    }
                                }
                            }
                        }

                        newsData.add(new NewsModelLocal(post.getId(),
                                smallThumb != null ? smallThumb.getUrl() : "",
                                largeThumb != null ? largeThumb.getUrl() : "",
                                imageTitle,
                                post.getTitle(),
                                post.getAbstract(),
                                copyrights
                                ));
                    }

                    insertData(newsData);

                    }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

                dataLD.postValue(null);
            }
        });

    }


}
