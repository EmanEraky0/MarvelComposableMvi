package com.example.marvelcomposablemvi.feature.marvelList.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Url(
    val type: String,
    val url: String
): Parcelable