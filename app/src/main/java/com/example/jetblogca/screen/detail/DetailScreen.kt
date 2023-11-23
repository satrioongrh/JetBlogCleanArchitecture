package com.example.jetblogca.screen.detail

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.core.domain.model.Blog
import com.example.jetblogca.screen.home.CircularImage

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailScreen(
    blog: Blog? = null,
    navController: NavHostController,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    var favoriteState by remember { mutableStateOf(blog?.isFavorite) }

    Log.d("123r4", "DetailScreen: $favoriteState")
    if (blog != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {

                CircularImage(50.0, 50.0, 25.0, blog.owner.picture)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${blog.owner.firstName} ${blog.owner.lastName}",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.Black
                    )
                )
                IconButton(onClick = {
                    favoriteState = !favoriteState!!
                    Log.d("222TAG", "DetailScreen: $favoriteState")
                    detailViewModel.setFavoriteBlog(blog, favoriteState!!)
                }) {
                    Icon(
                        imageVector = if (favoriteState!!) {
                            Icons.Default.Favorite
                        } else {
                            Icons.Default.FavoriteBorder
                        },
                        contentDescription = "favorite"
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                painter = rememberImagePainter(data = blog.image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = blog.text,
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Black
                )
            )
            FlowRow {
                blog.tags.forEach {
                    TagItem(it = it)
                }
            }
        }
    }
}

@Composable
fun TagItem(it: String) {

    Card(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(8.dp), shape = RoundedCornerShape(
            40.dp
        ),
        border = BorderStroke(0.5.dp, color = Color.Gray)
    ) {
        Text(text = it, style = TextStyle(color = Color.Black), modifier = Modifier.padding(12.dp))
    }

}

