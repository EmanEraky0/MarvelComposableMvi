package com.example.marvelcomposablemvi.feature.details.view

import com.example.marvelcomposablemvi.feature.marvelList.domain.models.Item

sealed class ResourceIntent {
    data class LoadResources(val section: String, val items: ArrayList<Item>):ResourceIntent()
}