package com.dan.mvvmwithrepoandhiltdemo.di

import com.dan.mvvmwithrepoandhiltdemo.repository.MainRepository
import com.dan.mvvmwithrepoandhiltdemo.retrofit.BlogRetrofit
import com.dan.mvvmwithrepoandhiltdemo.room.BlogDao
import com.dan.mvvmwithrepoandhiltdemo.room.CacheMapper
import com.dan.mvvmwithrepoandhiltdemo.util.NetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Dan Kim
 */

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        blogRetrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper

    ): MainRepository {
        return MainRepository(
            blogDao = blogDao,
            blogRetrofit = blogRetrofit,
            cacheMapper = cacheMapper,
            networkMapper = networkMapper
        )
    }
}