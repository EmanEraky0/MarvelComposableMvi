package com.example.marvelComposableMvi.feature.details.data.dataSource

import com.example.marvelComposableMvi.feature.marvelList.domain.models.AllMarvels


interface DetailDataSource {
    suspend fun getDetailCharacter(characterId:Int, ts:String, hash:String): AllMarvels
    suspend fun getResourceImage(url:String, ts:String, hash:String): AllMarvels

}