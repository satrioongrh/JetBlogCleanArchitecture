package com.example.core.data.network.api

import com.example.core.Utils.Constant
import com.example.core.data.network.model.BlogsDTO
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("post")
    suspend fun getBlogs (
        @Header("app-id") appId:String= Constant.APP_ID
    ) : BlogsDTO

}