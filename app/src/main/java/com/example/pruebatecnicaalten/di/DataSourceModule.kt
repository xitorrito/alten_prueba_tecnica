package com.example.pruebatecnicaalten.di

import com.example.pruebatecnicaalten.data.datasource.IUsersApiDataSource
import com.example.pruebatecnicaalten.data.datasource.UsersApiDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideUsersApiDataSource(): IUsersApiDataSource = UsersApiDataSource()

}