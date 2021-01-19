package com.paras.mostviewednews.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.paras.mostviewednews.data.local.NewsModelLocal;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNewsData(NewsModelLocal newsModelLocal);

    @Query("SELECT * FROM news_table")
    LiveData<List<NewsModelLocal>> getMostViewed();

    @Query("DELETE FROM news_table")
    void clearAll();
}
