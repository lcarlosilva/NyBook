package com.example.nybooks.data.response

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

data class BookBodyResponse(
    @SerializedName("results")
    val bookResultsResponse: List<BookResultsResponse>
)