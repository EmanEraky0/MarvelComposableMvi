package com.example.marvelcomposablemvi.utils

import com.example.marvelcomposablemvi.feature.marvelList.domain.models.Character

sealed class ResultState {
    data class Success(val characters: List<Character>) : ResultState()
    data class Error(val message: String) : ResultState()
    data object Empty : ResultState()
}