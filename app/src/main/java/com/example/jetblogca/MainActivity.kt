package com.example.jetblogca

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.core.domain.model.Blog
import com.example.jetblogca.navigation.Navigation
import com.example.jetblogca.ui.theme.JetBlogCATheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBlogCATheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val isFromFav = intent.getBooleanExtra("fromfav", false)

                    val blog = if (Build.VERSION.SDK_INT >= 33) {
                        intent.getParcelableExtra<Blog>("blogf", Blog::class.java)
                    } else {
                        @Suppress("DEPRECATION")
                        intent.getParcelableExtra<Blog>("blogf")
                    }

                    Log.d("0212", "onCreate: $isFromFav, $blog")

                    val navController = rememberNavController()
                    Navigation(navController = navController, isFromFav, blogf = blog)
                }
            }
        }
    }
}