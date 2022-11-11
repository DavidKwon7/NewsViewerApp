package com.example.newsviewerapp.di

import android.content.Context
import androidx.room.Room
import com.example.local.NewsDao
import com.example.remote.NewsDataBase
import com.example.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): NewsDataBase {
        return Room.databaseBuilder(context, NewsDataBase::class.java, Constants.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(newsDataBase: NewsDataBase): NewsDao {
        return newsDataBase.getNewsDao()
    }
}