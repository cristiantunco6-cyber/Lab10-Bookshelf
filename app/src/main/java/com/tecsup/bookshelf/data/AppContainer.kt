package com.tecsup.bookshelf.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Contenedor de dependencias: crea Retrofit, el servicio y el repositorio una sola vez
interface AppContainer {
    val booksRepository: BooksRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://openlibrary.org/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitService: BookshelfApiService by lazy {
        retrofit.create(BookshelfApiService::class.java)
    }

    override val booksRepository: BooksRepository by lazy {
        NetworkBooksRepository(retrofitService)
    }
}
