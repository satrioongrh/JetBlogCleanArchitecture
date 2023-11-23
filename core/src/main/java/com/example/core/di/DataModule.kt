package com.example.core.di

import com.example.core.Utils.Constant
import com.example.core.data.network.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    fun provideApiService(): ApiService {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
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