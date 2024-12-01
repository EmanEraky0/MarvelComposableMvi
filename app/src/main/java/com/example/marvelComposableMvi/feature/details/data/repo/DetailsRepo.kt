package com.example.marvelComposableMvi.feature.details.data.repo

import com.example.marvelComposableMvi.feature.marvelList.domain.models.Data

interface DetailsRepo {
    suspend fun getDetailCharacter(characterId:Int, ts:String, hash:String): Data
   suspend fun getResourceImage(url: String, ts: String, hash: String): Data
}