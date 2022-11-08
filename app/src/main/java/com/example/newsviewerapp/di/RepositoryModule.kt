package com.example.newsviewerapp.di

import com.example.domain.repository.SearchNewsRepository
import com.example.repository.SearchNewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSearchNewsRepository(
        articleNewsRepositoryImpl: SearchNewsRepositoryImpl
    ): SearchNewsRepository

}