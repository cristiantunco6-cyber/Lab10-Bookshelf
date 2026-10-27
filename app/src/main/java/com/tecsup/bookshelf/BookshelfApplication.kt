package com.tecsup.bookshelf

import android.app.Application
import com.tecsup.bookshelf.data.AppContainer
import com.tecsup.bookshelf.data.DefaultAppContainer

class BookshelfApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
