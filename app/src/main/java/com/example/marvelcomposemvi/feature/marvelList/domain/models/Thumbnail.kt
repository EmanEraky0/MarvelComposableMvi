package com.example.marvelcomposablemvi.feature.marvelList.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thumbnail(
    val extension :String ="",
    val path :String ="",
): Parcelable
