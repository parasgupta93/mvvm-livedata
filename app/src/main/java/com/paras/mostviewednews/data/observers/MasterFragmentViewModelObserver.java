package com.paras.mostviewednews.data.observers;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.paras.mostviewednews.BR;
import com.paras.mostviewednews.data.local.NewsModelLocal;

public class MasterFragmentViewModelObserver extends BaseObservable {

    private NewsModelLocal newsModelLocal = new NewsModelLocal();
    private String toolbarTitle = "";
    private boolean isLoading;

    @Bindable
    public NewsModelLocal getNewsModelLocal() {
        return newsModelLocal;
    }

    @Bindable
    public void setNewsModelLocal(NewsModelLocal newsModelLocal) {
        this.newsModelLocal = newsModelLocal;
        notifyPropertyChanged(BR.newsModelLocal);
    }

    @Bindable
    public String getToolbarTitle() {
        return toolbarTitle;
    }

    @Bindable
    public void setToolbarTitle(String toolbarTitle) {
        this.toolbarTitle = toolbarTitle;
        notifyPropertyChanged(BR.toolbarTitle);
    }

    @Bindable
    public boolean isLoading() {
        return isLoading;
    }

    @Bindable
    public void setLoading(boolean loading) {
        isLoading = loading;
        notifyPropertyChanged(BR.loading);
    }
}
