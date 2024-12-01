package com.example.marvelComposableMvi.feature.marvelList.data.dataSource

import com.example.marvelComposableMvi.feature.marvelList.domain.models.AllMarvels


interface ModuleDataSource {
    suspend fun getAllCharacters(name:String?, ts:String, hash:String, limit: Int, offset: Int): AllMarvels

}