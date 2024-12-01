package com.example.marvelComposableMvi.feature.details.view

import com.example.marvelComposableMvi.feature.marvelList.domain.models.Item

sealed class ResourceIntent {
    data class LoadResources(val section: String, val items: ArrayList<Item>):ResourceIntent()
}