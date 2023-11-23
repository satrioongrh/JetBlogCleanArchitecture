package com.example.jetblogca.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.core.domain.model.Blog
import com.example.jetblogca.screen.detail.DetailScreen
import com.example.jetblogca.screen.home.HomeScreen

@Composable
fun Navigation(navController: NavHostController, from: Boolean, blogf: Blog? = null) {
//    val detailViewModel: DetailViewModel = hiltViewModel()
    NavHost(
        navController = navController,
//        startDestination = Screen.Home.route
        startDestination = if (from) {
            Screen.Detail.route
        } else {
            Screen.Home.route
        }
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Detail.route) {
            val blog: Blog? = if (from) {
                blogf
            } else {
                navController.previousBackStackEntry?.savedStateHandle?.get<Blog>("blog")
            }
            DetailScreen(blog,navController)
        }
    }
}