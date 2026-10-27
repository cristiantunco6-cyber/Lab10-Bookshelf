package com.tecsup.bookshelf.data

import retrofit2.http.GET
import retrofit2.http.Query

interface BookshelfApiService {
    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("fields") fields: String = "key,title,author_name,first_publish_year,edition_count,cover_i,subject",
        @Query("limit") limit: Int = 40
    ): SearchResponse
}
