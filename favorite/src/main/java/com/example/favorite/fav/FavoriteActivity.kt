package com.example.favorite.fav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import com.example.favorite.di.DaggerFavoriteModule
import com.example.favorite.di.ViewModelFactory
import com.example.favorite.screen.FavoriteScreen
import com.example.favorite.screen.FavoriteViewModel
import com.example.jetblogca.di.FavoriteModuleDependencies
import com.example.jetblogca.ui.theme.JetBlogCATheme
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : ComponentActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            DaggerFavoriteModule.builder()
                .context(this)
                .appDependencies(
                    EntryPointAccessors.fromApplication(
                        applicationContext,
                        FavoriteModuleDependencies::class.java
                    )
                )
                .build()
                .inject(this)
            DaggerFavoriteModule.builder()

        setContent {
            JetBlogCATheme {
                val context = LocalContext.current
                FavoriteScreen(viewModel = viewModel)
            }
        }
    }
}