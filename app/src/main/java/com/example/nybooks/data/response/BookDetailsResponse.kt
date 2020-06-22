package com.example.nybooks.data.response

import androidx.lifecycle.GeneratedAdapter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookDetailsResponse (
    @Json(name = "title")
    var title: String,
    @Json(name = "author")
    var author: String,
    @Json(name = "description")
    var description: String
)