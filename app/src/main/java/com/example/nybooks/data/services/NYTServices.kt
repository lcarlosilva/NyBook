package com.example.nybooks.data.services

import com.example.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTServices {

    @GET("books/v3/lists.json")
    fun getBooks(@Query("list") list: String = "hardcover-fiction"): Call<BookBodyResponse>

}