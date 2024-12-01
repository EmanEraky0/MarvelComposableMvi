package com.example.marvelComposableMvi.feature.details.state

import com.example.marvelComposableMvi.feature.marvelList.domain.models.Item

data class ResourceState(
    var isLoadingComics :Boolean =false,
    val comics: List<Item> = emptyList(),

    var isLoadingSeries :Boolean =false,
    val series: List<Item> = emptyList(),

    var isLoadingStories :Boolean =false,
    val stories: List<Item> = emptyList(),

    var isLoadingEvent :Boolean =false,
    val events: List<Item> = emptyList(),

    val errorMessage :String ="",
)
