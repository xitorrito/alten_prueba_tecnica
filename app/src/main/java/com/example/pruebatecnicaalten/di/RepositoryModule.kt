package com.example.pruebatecnicaalten.di

import com.example.pruebatecnicaalten.data.datasource.IUsersApiDataSource
import com.example.pruebatecnicaalten.data.repository.IUsersRepository
import com.example.pruebatecnicaalten.data.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUsersRepository(dataSource: IUsersApiDataSource): IUsersRepository =
        UsersRepository(dataSource)
}