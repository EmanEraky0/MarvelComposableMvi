package com.example.marvelComposableMvi.feature.marvelList.state

import com.example.marvelComposableMvi.feature.marvelList.domain.models.Character

data class MarvelState (
    val characters : List<Character> = listOf(),
    val isLoading :Boolean =false,
    val errorMessage: String? = null,
    val hasMoreToLoad: Boolean = true
)