package com.example.marvelcomposablemvi.feature.marvelList.data

import com.example.marvelcomposablemvi.BuildConfig
import com.example.marvelcomposablemvi.feature.marvelList.domain.models.AllMarvels
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("characters?apikey="+ BuildConfig.Public_Key)
    suspend fun getAllCharacters(
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("name") name: String? = null,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): AllMarvels
}