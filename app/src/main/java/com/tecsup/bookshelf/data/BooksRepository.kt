package com.tecsup.bookshelf.data

interface BooksRepository {
    suspend fun searchBooks(query: String): List<Book>
}

class NetworkBooksRepository(
    private val api: BookshelfApiService
) : BooksRepository {
    // Solo se muestran los libros que tienen portada
    override suspend fun searchBooks(query: String): List<Book> =
        api.searchBooks(query).docs.filter { it.coverId != null }
}
