package com.example.marvelComposableMvi.feature.marvelList.viewModels

import androidx.lifecycle.viewModelScope
import com.example.marvelComposableMvi.feature.marvelList.domain.useCases.CharacterUseCase
import com.example.marvelComposableMvi.feature.marvelList.intent.MarvelIntent
import com.example.marvelComposableMvi.feature.marvelList.state.MarvelState
import com.example.marvelComposableMvi.utils.BaseViewModel
import com.example.marvelComposableMvi.utils.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MarvelViewModel(private val useCase : CharacterUseCase) :BaseViewModel<MarvelState,MarvelIntent>() {

    override fun getInitialState(): MarvelState =MarvelState()

    override fun processIntents(intent: MarvelIntent) {
        when (intent) {
           is MarvelIntent.FetchMarvelList->{
               useCase.reset()
                fetchCharacters(null)
            }

           is MarvelIntent.LoadMore ->{
               loadNextPage()
            }

            is MarvelIntent.SearchCharacter -> {
                useCase.reset()
                fetchCharacters(intent.name)
            }
        }
    }

    private fun loadNextPage(){
        useCase.currentOffset ++
        fetchCharacters(null)
    }
    private fun fetchCharacters(name:String?) {
        viewModelScope.launch(Dispatchers.IO) {
            updateState { it.copy(isLoading = true) }

            when (val result = useCase.execute(name)) {
                is ResultState.Success -> {
                    updateState { oldState ->
                        oldState.copy(
                            characters = result.characters, // Append new characters
                            isLoading = false,
                            hasMoreToLoad = result.characters.isNotEmpty()
                        )
                    }
                }

                is ResultState.Error -> {
                    updateState { oldState ->
                        oldState.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                }

                ResultState.Empty -> {
                    updateState { it.copy(isLoading = false, hasMoreToLoad = false) }
                }
            }
        }
    }


}