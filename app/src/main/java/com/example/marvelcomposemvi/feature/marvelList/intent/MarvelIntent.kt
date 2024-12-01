package com.example.marvelcomposablemvi.feature.marvelList.intent

sealed class MarvelIntent {
    data object FetchMarvelList :MarvelIntent()
    data object LoadMore :MarvelIntent()
    data class SearchCharacter(val name:String) :MarvelIntent()
}