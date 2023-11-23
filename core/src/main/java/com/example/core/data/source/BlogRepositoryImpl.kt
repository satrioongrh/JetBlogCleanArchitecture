package com.example.core.data.source

import com.example.core.Utils.AppExecutors
import com.example.core.Utils.DataMapper
import com.example.core.data.local.LocalDataSource
import com.example.core.data.network.RemoteDataSource
import com.example.core.data.network.api.ApiResponse
import com.example.core.data.network.model.BlogDTO
import com.example.core.domain.model.Blog
import com.example.core.domain.repository.BlogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BlogRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExcecutor: AppExecutors
) : BlogRepository {

    //    override suspend fun getAllBlogs(): Flow<Resource<List<Blog>>> {
//        return flow {
//            emit(Resource.Loading)
//            try {
//                val response = apiService.getBlogs()
//                if (response.isSuccessful) {
//                    val bloglist = response.body()?.data?.toDomain() ?: emptyList()
//                    emit(Resource.Success(bloglist))
//                } else {
//                    emit(Resource.Error(response.message()))
//                }
//            } catch (e: Exception) {
//                emit(Resource.Error(e.message.toString()))
//            }
//        }.flowOn(Dispatchers.IO)
//    }
    override suspend fun getAllBlogs(): Flow<Resource<List<Blog>>> {
        return object : NetworkBoundResource<List<Blog>, List<BlogDTO>>(),
            Flow <Resource<List<Blog>>>{
            override fun loadFromDB(): Flow<List<Blog>> {
                return localDataSource.getAllBlogs().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<BlogDTO?>?>> {
                return remoteDataSource.getAllBlogs()
            }

            override suspend fun saveCallResult(data: List<BlogDTO?>?) {
                withContext(Dispatchers.IO){
                    localDataSource.insertBlog(DataMapper.mapResponsesToEntities(data))
                }
            }

            override fun shouldFetch(data: List<Blog>?): Boolean {
                return data.isNullOrEmpty()
            }

            override suspend fun collect(collector: FlowCollector<Resource<List<Blog>>>) {

            }

        }.asFlow()
    }

    override fun getFavorite(): Flow<List<Blog>> {
        return localDataSource.getFavorite().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavorite(blog: Blog, state: Boolean) {
        val newsEntity = DataMapper.mapDomainToEntity(blog)
        appExcecutor.diskIO().execute {
            localDataSource.setFavoriteBlogs(newsEntity,state)
        }
    }
}