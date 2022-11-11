package com.example.newsviewerapp.di

import com.example.domain.repository.CategoryNewsRepository
import com.example.domain.repository.LocalRepository
import com.example.domain.repository.SearchNewsRepository
import com.example.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindLocalDataSource(
        localDataSourceImpl: LocalDataSourceImpl
    ): LocalDataSource

    @Binds
    abstract fun bindLocalRepository(
        localRepositoryImpl: LocalRepositoryImpl
    ): LocalRepository

    @Binds
    abstract fun bindCategoryNewsRepository(
        categoryNewsRepositoryImpl: CategoryNewsRepositoryImpl
    ): CategoryNewsRepository

    @Binds
    abstract fun bindSearchNewsRepository(
        articleNewsRepositoryImpl: SearchNewsRepositoryImpl
    ): SearchNewsRepository

}