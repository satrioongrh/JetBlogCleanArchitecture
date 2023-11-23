package com.example.jetblogca.screen.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.source.Resource
import com.example.core.domain.usecases.BlogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getBlogUseCase: BlogUseCase) : ViewModel() {

    private val _blogs= mutableStateOf<HomeState>(HomeState())
    val blogs: State<HomeState> = _blogs

    init {
        Log.d("MyApp", "homeviewmodel init ")

        getBlogs()
    }

    fun getBlogs() {
        Log.d("MyApp", "getblog exe")
        viewModelScope.launch {
            Log.d("MyApp", "getblog vscope")

            getBlogUseCase.getAllBlog().collect {
                Log.d("MyApp", "onEach executed: $it")
                when(it){
                    is Resource.Loading -> {
                        _blogs.value = HomeState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _blogs.value = HomeState(data = it.data)
                    }
                    is Resource.Error -> {
                        _blogs.value = HomeState(error = it.error.toString())
                    }
                    else -> {}
                }
            }
        }
    }
}