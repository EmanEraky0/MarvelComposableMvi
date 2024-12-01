package com.example.marvelcomposablemvi.feature.marvelList.data.dataSource

import com.example.marvelcomposablemvi.feature.marvelList.domain.models.AllMarvels


interface ModuleDataSource {
    suspend fun getAllCharacters(name:String?, ts:String, hash:String, limit: Int, offset: Int): AllMarvels

}