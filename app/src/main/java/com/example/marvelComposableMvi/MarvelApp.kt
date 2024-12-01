package com.example.marvelComposableMvi

import android.app.Application
import com.example.marvelComposableMvi.constants.appModule
import com.example.marvelComposableMvi.feature.details.data.di.detailsModule
import com.example.marvelComposableMvi.feature.marvelList.data.di.listModule
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