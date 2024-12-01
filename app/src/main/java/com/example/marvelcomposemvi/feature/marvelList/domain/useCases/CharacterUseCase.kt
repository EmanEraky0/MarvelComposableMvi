package com.example.marvelcomposablemvi.feature.marvelList.domain.useCases

import com.example.marvelcomposablemvi.BuildConfig
import com.example.marvelcomposablemvi.feature.marvelList.data.repo.CharacterRepoImpl
import com.example.marvelcomposablemvi.feature.marvelList.domain.models.Character
import com.example.marvelcomposablemvi.feature.marvelList.state.MarvelState
import com.example.marvelcomposablemvi.utils.ResultState
import com.example.marvelcomposablemvi.utils.generateHash

class CharacterUseCase(private val repo: CharacterRepoImpl) {
    val ts = System.currentTimeMillis().toString()
    val hash = generateHash(ts, BuildConfig.Private_Key, BuildConfig.Public_Key)

    val characters: ArrayList<Character> = arrayListOf()
    private var isLastPage = false
    private var isLoadingMore = false
    var currentOffset = 0
    private val limit = 20

    suspend fun execute(name: String?): ResultState {
        if (isLoadingMore || isLastPage) {
            return ResultState.Empty // No new data can be fetched
        }

        isLoadingMore = true

        return try {
            val result =
                repo.getAllCharacters(name, ts, hash, offset = currentOffset, limit = limit)
            if (result.results.isEmpty())
                isLastPage = true
            else
                currentOffset
            characters.addAll(result.results)
            ResultState.Success(characters) // Return only the new characters
        } catch (e: Exception) {
            ResultState.Error(e.message ?: "An error occurred")
        } finally {
            isLoadingMore = false
        }
    }

    fun reset() {
        characters.clear()
    }
}




