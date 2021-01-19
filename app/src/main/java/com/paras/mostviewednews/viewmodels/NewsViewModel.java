package com.paras.mostviewednews.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.paras.mostviewednews.data.local.NewsModelLocal;
import com.paras.mostviewednews.data.observers.MasterFragmentViewModelObserver;

import com.paras.mostviewednews.repositories.NewsRepository;
import com.paras.mostviewednews.utils.Event;
import com.paras.mostviewednews.views.DetailFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NewsViewModel extends ViewModel {

    public MasterFragmentViewModelObserver masterFragmentViewModelObserver = new MasterFragmentViewModelObserver();
    public MutableLiveData<Event<Type>> navigationLiveData = new MutableLiveData<>();
    public MutableLiveData<ArrayList<NewsModelLocal>> items = new MutableLiveData<>();

    NewsRepository newsRepository;

    @ViewModelInject
    public NewsViewModel(NewsRepository newsRepository) {

        this.newsRepository = newsRepository;
        refreshData();
    }

   public LiveData<List<NewsModelLocal>> getNewsList(){
     return newsRepository.mListLiveData;
    }

    public void onNewTapped(NewsModelLocal newsModelLocal){

        masterFragmentViewModelObserver.setNewsModelLocal(newsModelLocal);
        navigationLiveData.postValue(new Event<>(DetailFragment.class));
    }

    public void refreshData(){

        masterFragmentViewModelObserver.setLoading(true);
        newsRepository.getMostViewedNews(items);
    }

}
