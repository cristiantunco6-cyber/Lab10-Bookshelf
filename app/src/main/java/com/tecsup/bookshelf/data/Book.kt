package com.tecsup.bookshelf.data

import com.google.gson.annotations.SerializedName

// Respuesta de GET search.json del API de Open Library
data class SearchResponse(
    @SerializedName("numFound") val numFound: Int,
    @SerializedName("docs") val docs: List<Book>
)

data class Book(
    @SerializedName("key") val key: String,
    @SerializedName("title") val title: String,
    @SerializedName("author_name") val authors: List<String>? = null,
    @SerializedName("first_publish_year") val firstPublishYear: Int? = null,
    @SerializedName("edition_count") val editionCount: Int? = null,
    @SerializedName("cover_i") val coverId: Long? = null,
    @SerializedName("subject") val subjects: List<String>? = null
) {
    // La portada se arma con el id: https://covers.openlibrary.org/b/id/{id}-M.jpg
    fun coverUrl(size: String = "M"): String? =
        coverId?.let { "https://covers.openlibrary.org/b/id/$it-$size.jpg" }

    val id: String get() = key.substringAfterLast("/")
}
