package com.example.marvelComposableMvi.feature.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.marvelComposableMvi.feature.details.compose.DetailsCharacter
import com.example.marvelComposableMvi.feature.details.intent.DetailsViewModel
import com.example.marvelComposableMvi.feature.marvelList.domain.models.Character
import com.example.marvelComposableMvi.feature.marvelList.intent.MarvelIntent
import com.example.marvelComposableMvi.ui.theme.MarvelComposableMviTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsMarvelFragment : Fragment() {
    private val viewModel: DetailsViewModel by viewModel()
    var character :Character? =null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        character = arguments?.getParcelable("character") ?: Character()
        setContent {
            MarvelComposableMviTheme {
                Column {
                    DetailsCharacter(character = character!!, sectionState =viewModel.state)
                }
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        character?.comics?.items?.let {
            sendIntentFetch(ResourceIntent.LoadResources("Comics", it))
        }
        character?.series?.items?.let {
            sendIntentFetch(ResourceIntent.LoadResources("Series", it))
        }
        character?.stories?.items?.let {
            sendIntentFetch(ResourceIntent.LoadResources("Stories", it))
        }
        character?.events?.items?.let {
            sendIntentFetch(ResourceIntent.LoadResources("Events", it))
        }
    }
    private fun sendIntentFetch(intent: ResourceIntent){
        lifecycleScope.launch { viewModel.intent.send(intent)  }
    }
}