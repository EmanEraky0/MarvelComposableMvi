package com.example.marvelcomposablemvi.feature.marvelList.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: ArrayList<Character>,
    val total: Int
):Parcelable