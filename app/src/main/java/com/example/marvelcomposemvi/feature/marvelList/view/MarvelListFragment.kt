package com.example.marvelcomposablemvi.feature.marvelList.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.marvelcomposablemvi.R
import com.example.marvelcomposablemvi.feature.marvelList.compose.MarvelScreen
import com.example.marvelcomposablemvi.feature.marvelList.compose.ToolbarWithSearch
import com.example.marvelcomposablemvi.feature.marvelList.intent.MarvelIntent
import com.example.marvelcomposablemvi.feature.marvelList.viewModels.MarvelViewModel
import com.example.marvelcomposablemvi.ui.theme.MarvelComposeMviTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarvelListFragment :Fragment() {
    private val viewModel: MarvelViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {

        setContent {
            MarvelComposeMviTheme {
                Column {
                    ToolbarWithSearch(onClickActionSearch ={name -> viewModel.processIntents(MarvelIntent.SearchCharacter(name)) },onClickCancel={viewModel.processIntents(MarvelIntent.FetchMarvelList)})
                    MarvelScreen(viewModel.state, sendIntent = {intent -> viewModel.processIntents(intent) } , onCharacterClick = { character ->
                        findNavController().navigate(
                            R.id.marvelDetailsFragment,
                            Bundle().apply { putParcelable("character", character) })
                    })

                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendIntentFetch(MarvelIntent.FetchMarvelList)
    }
    private fun sendIntentFetch(intent: MarvelIntent){
        lifecycleScope.launch { viewModel.intent.send(intent)  }
    }
}