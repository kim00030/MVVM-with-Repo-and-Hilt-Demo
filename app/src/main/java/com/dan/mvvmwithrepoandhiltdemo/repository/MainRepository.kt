package com.dan.mvvmwithrepoandhiltdemo.repository

import com.dan.mvvmwithrepoandhiltdemo.model.Blog
import com.dan.mvvmwithrepoandhiltdemo.retrofit.BlogRetrofit
import com.dan.mvvmwithrepoandhiltdemo.room.BlogDao
import com.dan.mvvmwithrepoandhiltdemo.room.CacheMapper
import com.dan.mvvmwithrepoandhiltdemo.util.DataState
import com.dan.mvvmwithrepoandhiltdemo.util.NetworkMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Dan Kim
 */
class MainRepository constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper

) {

    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        kotlinx.coroutines.delay(1000)//just show progress bar. DO NOT DO it on production

        try {
            //get data fom server
            val networkBlogs = blogRetrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            //store it into db
            blogs.forEach {
                blogDao.insert(cacheMapper.mapToEntity(it))
            }
            val cachedBlog = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlog)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

}