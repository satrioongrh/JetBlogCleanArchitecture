package com.example.core.data.network

import android.util.Log
import com.example.core.data.network.api.ApiResponse
import com.example.core.data.network.api.ApiService
import com.example.core.data.network.model.BlogDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllBlogs(): Flow<ApiResponse<List<BlogDTO?>?>> {
//        return flow {
//            try {
//                val response = apiService.getBlogs()
//                val bloglist = response.data
//                if (bloglist?.isNotEmpty() == true) {
//                    emit(ApiResponse.Success(response.data))
//                } else {
//                    emit(ApiResponse.Empty)
//                }
//            } catch (e: Exception) {
//                emit(Resource.Error(e.message.toString()))
//            }
//        }.flowOn(Dispatchers.IO)
        return flow {
            try{
                val response = apiService.getBlogs()
                val data = response.data
                if (data?.isNotEmpty() == true){
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}