package com.example.core.di

import com.example.core.Utils.Constant
import com.example.core.data.network.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    fun provideApiService(): ApiService {
        val hostname = "dummyapi.io"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/F6jTih9VkkYZS8yuYqeU/4DUGehJ+niBGkkQ1yg8H3U=")
            .add(hostname, "sha256/cXjPgKdVe6iojP8s0YQJ3rtmDFHTnYZxcYvmYGFiYME=")
            .add(hostname, "sha256/hxqRlPTu1bMS/0DITB1SSu0vd4u/8l8TjPgfaAp63Gc=")
            .add(hostname, "sha256/K87oWBWM9UZfyddvDfoxL+8lpNyoUB2ptGtn0fv6G2Q=")
            .build()
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }

//    @Provides
//    fun provideRemoteDataSource(apiService: ApiService) : RemoteDataSource =
//        RemoteDataSource(apiService)
//
//    @Provides
//    fun provideLocalDataSource() : LocalDataSource =
//        LocalDataSource()

//    @Provides
//    fun provideBlogRepositoryImpl(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource, ): BlogRepository =
//        BlogRepositoryImpl()

}