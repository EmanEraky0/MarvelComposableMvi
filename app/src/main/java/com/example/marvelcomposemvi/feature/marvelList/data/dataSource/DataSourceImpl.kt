package com.example.marvelcomposablemvi.feature.marvelList.data.dataSource

import com.example.marvelcomposablemvi.feature.marvelList.data.ApiService
import com.example.marvelcomposablemvi.feature.marvelList.domain.models.AllMarvels

class DataSourceImpl(private val apiService: ApiService) : ModuleDataSource {
    override suspend fun getAllCharacters(name: String?, ts: String, hash: String, limit: Int, offset: Int): AllMarvels =
            apiService.getAllCharacters(name =name, ts = ts, hash = hash, limit = limit, offset = offset)
}