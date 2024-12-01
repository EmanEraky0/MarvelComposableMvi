package com.example.marvelcomposablemvi.feature.marvelList.state

import com.example.marvelcomposablemvi.feature.marvelList.domain.models.Character

data class MarvelState (
    val characters : List<Character> = listOf(),
    val isLoading :Boolean =false,
    val errorMessage: String? = null,
    val hasMoreToLoad: Boolean = true
)