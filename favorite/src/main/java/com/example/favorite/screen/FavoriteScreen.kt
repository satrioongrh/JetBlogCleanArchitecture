package com.example.favorite.screen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.jetblogca.MainActivity
import com.example.jetblogca.screen.home.PostItem


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel,
) {
    val blogFavorite by viewModel.moviesFavorite.observeAsState(initial = emptyList())
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Favorite Blog") }
            )
        }
    ) {
        if (blogFavorite.isNotEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(it)) {
                blogFavorite.let {
                    items(it) {
                        PostItem(it, onClick = {
                            context.startActivity(
                                Intent(
                                    context,
                                    MainActivity::class.java
                                ).putExtra("blogf", it)
                                    .putExtra("fromfav", true)
                            )
                        }
                        )
                    }
                }
            }
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize().padding(it)
            ) {
                Text(text = "No favorite movies found")
            }
        }
    }
}
