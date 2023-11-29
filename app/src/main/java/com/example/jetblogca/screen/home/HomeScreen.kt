package com.example.jetblogca.screen.home

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.core.domain.model.Blog
import com.example.jetblogca.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, homeViewModel: HomeViewModel = hiltViewModel()) {

    val res = homeViewModel.blogs.value

    if (res.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

    if (res.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = res.error.toString(), modifier = Modifier.align(Alignment.Center))
        }
    }

    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                try {
                    context.startActivity(
                        Intent(
                            context,
                            Class.forName("com.example.favorite.fav.FavoriteActivity")
                        )
                    )
                } catch (e: Exception) {
                    Toast.makeText(
                        context,
                        "Fitur ini belum ada, harap menunggu",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "favorite")
            }
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            res.data?.let {
                items(it) {
                    PostItem(it, onClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            key = "blog",
                            value = it
                        )
                        navController.navigate(Screen.Detail.route)
                    })
                }
            }
        }
    }

}

@Composable
fun PostItem(it: Blog, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            CircularImage(50.0, 50.0, 25.0, it.owner.picture)
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "${it.owner.firstName} ${it.owner.lastName}")
        }
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            painter = rememberAsyncImagePainter(model = it.image), contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            text = it.text,
            modifier = Modifier.padding(12.dp),
            style = TextStyle(color = Color.Gray, fontSize = 20.sp)
        )
        Divider()
    }

}

@Composable
fun CircularImage(width: Double, height: Double, radius: Double, imageUrl: String) {
    Card(
        modifier = Modifier
            .width(width = width.dp)
            .height(height = height.dp), shape = RoundedCornerShape(radius.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = imageUrl), contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}
