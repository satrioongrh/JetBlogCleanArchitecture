package com.example.favorite.fav

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.di.ViewModelFactory
import com.example.favorite.screen.FavoriteScreen
import com.example.favorite.screen.FavoriteViewModel
import javax.inject.Inject


class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.composeView.apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                // In Compose world
                MaterialTheme {
                    FavoriteScreen(viewModel = viewModel)
                }
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        DaggerFavoriteComponent.builder()
//            .context(context)
//            .appDependencies(
//                EntryPointAccessors.fromApplication(
//                    requireActivity().applicationContext,
//                    FavoriteModuleDepedencies::class.java
//                )
//            )
//            .build()
//            .inject(this)
//        DaggerFavoriteComponent.builder()
    }

}