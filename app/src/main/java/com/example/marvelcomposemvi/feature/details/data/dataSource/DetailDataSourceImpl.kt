package com.example.marvelcomposablemvi.feature.details.data.dataSource

import com.example.marvelcomposablemvi.feature.details.data.DetailsApiService
import com.example.marvelcomposablemvi.feature.marvelList.domain.models.AllMarvels

class DetailDataSourceImpl(private val apiService: DetailsApiService) : DetailDataSource {
    override suspend fun getDetailCharacter(
        characterId: Int,
        ts: String,
        hash: String
    ): AllMarvels =
        apiService.getDetailCharacter(characterId = characterId, ts = ts, hash = hash)


    override suspend fun getResourceImage(url: String, ts: String, hash: String): AllMarvels =
        apiService.getResourceImage(url = url, ts = ts, hash = hash)

}