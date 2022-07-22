package com.github.astat1cc.holybibleapp.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BibleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BibleApp)
            modules(retrofitModule, booksModule, roomModule)
        }
    }
}