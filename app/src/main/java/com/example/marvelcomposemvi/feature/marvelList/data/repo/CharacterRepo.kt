package com.example.marvelcomposablemvi.feature.marvelList.data.repo

import com.example.marvelcomposablemvi.feature.marvelList.domain.models.Data

interface CharacterRepo {
    suspend fun getAllCharacters(name:String?,ts: String, hash: String,limit: Int, offset: Int): Data

}