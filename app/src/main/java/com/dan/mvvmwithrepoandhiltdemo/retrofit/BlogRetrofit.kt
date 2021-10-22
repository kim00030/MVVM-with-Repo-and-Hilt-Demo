package com.dan.mvvmwithrepoandhiltdemo.retrofit

import retrofit2.http.GET

//https://open-api.xyz/placeholder/blogs
interface BlogRetrofit {

    @GET("blogs")
    suspend fun get(): List<BlogNetworkEntity>
}