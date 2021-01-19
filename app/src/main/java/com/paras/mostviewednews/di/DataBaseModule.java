package com.paras.mostviewednews.di;

import android.app.Application;
import androidx.room.Room;
import com.paras.mostviewednews.data.db.NewsDB;
import com.paras.mostviewednews.data.db.NewsDao;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DataBaseModule {

    @Provides
    @Singleton
    public static NewsDB providePokemonDB(Application application){
        return Room.databaseBuilder(application,NewsDB.class,"Favorite Database")
                .fallbackToDestructiveMigration()
            //    .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static NewsDao providePokeDao(NewsDB newsDB){
        return newsDB.newsDao();
    }
}
