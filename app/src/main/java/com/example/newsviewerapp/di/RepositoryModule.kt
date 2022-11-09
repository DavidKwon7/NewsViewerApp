package com.example.newsviewerapp.di

import com.example.domain.repository.CategoryNewsRepository
import com.example.domain.repository.SearchNewsRepository
import com.example.repository.CategoryNewsRepositoryImpl
import com.example.repository.SearchNewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCategoryNewsRepository(
        categoryNewsRepositoryImpl: CategoryNewsRepositoryImpl
    ): CategoryNewsRepository

    @Binds
    abstract fun bindSearchNewsRepository(
        articleNewsRepositoryImpl: SearchNewsRepositoryImpl
    ): SearchNewsRepository

}