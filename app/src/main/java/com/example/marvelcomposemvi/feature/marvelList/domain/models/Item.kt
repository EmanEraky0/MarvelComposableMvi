package com.example.marvelcomposablemvi.feature.marvelList.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    var name: String="",
    var resourceURI: String="",
    val type: String=""
):Parcelable