package com.example.marvelComposableMvi.feature.details.domain

import com.example.marvelComposableMvi.BuildConfig
import com.example.marvelComposableMvi.feature.details.data.repo.DetailsRepoImpl
import com.example.marvelComposableMvi.feature.marvelList.domain.models.Item
import com.example.marvelComposableMvi.utils.generateHash


class DetailsUseCase(private val repoImpl: DetailsRepoImpl) {
    val ts = System.currentTimeMillis().toString()
    val hash = generateHash(ts, BuildConfig.Private_Key, BuildConfig.Public_Key)

    suspend fun fetchDataForSection(items: List<Item>): List<Item> {
        val sectionItems = mutableListOf<Item>()

        for (item in items) {
            try {
                val result = repoImpl.getResourceImage(url =item.resourceURI.replace("http", "https"),ts=ts, hash=hash)

                val data = result.results.getOrNull(0)
                if (data?.thumbnail != null) {
                    val itemToAdd = Item(
                        name = data.title,
                        resourceURI = "${data.thumbnail.path}.${data.thumbnail.extension}"
                    )
                    sectionItems.add(itemToAdd)
                }
            } catch (e: Exception) {
                println("Error fetching item for : ${item.resourceURI}, ${e.message}")
            }
        }

        return sectionItems
    }

}