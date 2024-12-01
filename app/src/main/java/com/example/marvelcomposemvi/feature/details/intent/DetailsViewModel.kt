package com.example.marvelcomposablemvi.feature.details.intent

import androidx.lifecycle.viewModelScope
import com.example.marvelcomposablemvi.feature.details.domain.DetailsUseCase
import com.example.marvelcomposablemvi.feature.details.state.ResourceState
import com.example.marvelcomposablemvi.feature.details.view.ResourceIntent
import com.example.marvelcomposablemvi.feature.marvelList.domain.models.Item
import com.example.marvelcomposablemvi.utils.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(private val detailUseCase: DetailsUseCase) :
    BaseViewModel<ResourceState, ResourceIntent>() {

    override fun getInitialState(): ResourceState  {
        val state =ResourceState()
        state.isLoadingComics =true
        state.isLoadingEvent =true
        state.isLoadingStories =true
        state.isLoadingSeries =true
      return state
    }

    override fun processIntents(intent: ResourceIntent) {
        when (intent) {
            is ResourceIntent.LoadResources -> {
                fetchSection(section= intent.section, items = intent.items)
            }
        }
    }

    private fun fetchSection(section: String, items: ArrayList<Item>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updateState { it.copy(isLoadingComics = true,isLoadingSeries = true,isLoadingStories = true,isLoadingEvent = true) }
                val res = detailUseCase.fetchDataForSection(items)
                updateState { oldState ->
                    when (section) {
                        "Comics" -> oldState.copy(isLoadingComics = false, comics = res)
                        "Series" -> oldState.copy(isLoadingSeries = false, series = res)
                        "Stories" -> oldState.copy(isLoadingStories = false, stories = res)
                        "Events" -> oldState.copy(isLoadingEvent = false, events = res)
                        else -> oldState
                    }
                }
            } catch (e: Exception) {
                updateState { oldState ->
                    oldState.copy(isLoadingComics = false,isLoadingSeries = false,
                        isLoadingStories = false,
                        isLoadingEvent = false,
                        errorMessage = e.message.toString())
                }
            }
        }
    }

}