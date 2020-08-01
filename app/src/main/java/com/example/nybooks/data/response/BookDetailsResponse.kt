package com.example.nybooks.data.response

import com.example.nybooks.data.model.Book
import com.google.gson.annotations.SerializedName

data class BookDetailsResponse (
    @SerializedName("title")
    var title: String,
    @SerializedName("author")
    var author: String,
    @SerializedName("description")
    var description: String
) {
    fun getBookModel() = Book(
        title = this.title,
        author = this.author,
        description = this.description
    )
}