package com.github.astat1cc.holybibleapp.core

import android.app.Application
import com.github.astat1cc.holybibleapp.data.dataModule
import com.github.astat1cc.holybibleapp.data.retrofitModule
import com.github.astat1cc.holybibleapp.data.roomModule
import com.github.astat1cc.holybibleapp.domain.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.github.astat1cc.holybibleapp.presentation.presentationModule

class BibleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BibleApp)
            modules(
                retrofitModule,
                roomModule,
                dataModule,
                domainModule,
                presentationModule
            )
        }
    }
}