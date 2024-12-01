package com.example.marvelcomposablemvi.feature.details.data.di

import com.example.marvelcomposablemvi.feature.details.data.DetailsApiService
import com.example.marvelcomposablemvi.feature.details.data.dataSource.DetailDataSourceImpl
import com.example.marvelcomposablemvi.feature.details.data.repo.DetailsRepoImpl
import com.example.marvelcomposablemvi.feature.details.domain.DetailsUseCase
import com.example.marvelcomposablemvi.feature.details.intent.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val detailsModule = module {
    single { DetailDataSourceImpl(get()) }
    single { DetailsRepoImpl(get()) }
    single { getApiService(get()) }
    single { DetailsUseCase(get()) }

    viewModel {
        DetailsViewModel(get())
    }

}


private fun getApiService(retrofit: Retrofit): DetailsApiService =
    retrofit.create(DetailsApiService::class.java)