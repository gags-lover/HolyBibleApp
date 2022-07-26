package com.github.astat1cc.holybibleapp.core

import android.app.Application
import com.github.astat1cc.holybibleapp.presentation.presentationModule
import org.koin.core.context.startKoin

class BibleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(presentationModule)
        }
    }
}