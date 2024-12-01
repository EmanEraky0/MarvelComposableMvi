package com.example.marvelcomposablemvi

import android.app.Application
import com.example.marvelcomposablemvi.constants.appModule
import com.example.marvelcomposablemvi.feature.details.data.di.detailsModule
import com.example.marvelcomposablemvi.feature.marvelList.data.di.listModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MarvelApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MarvelApp)
            modules(
                appModule,
                listModule,
                detailsModule
            )
        }
    }

}