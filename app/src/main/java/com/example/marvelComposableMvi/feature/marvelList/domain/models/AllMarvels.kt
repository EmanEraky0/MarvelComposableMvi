package com.example.marvelComposableMvi.feature.marvelList.domain.models

data class AllMarvels(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)
