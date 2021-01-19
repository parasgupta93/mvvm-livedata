package com.paras.mostviewednews.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.paras.mostviewednews.data.local.NewsModelLocal;

@Database(entities = {NewsModelLocal.class},version = 2,exportSchema = false)
public abstract class NewsDB extends RoomDatabase {
    public abstract NewsDao newsDao();
}
