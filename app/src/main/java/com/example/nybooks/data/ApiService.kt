package com.example.nybooks.data

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object ApiService {

    fun initRetrofit(): Retrofit {
        /*var moshi = Moshi.Builder()
            .add()
            .build()*/

        return Retrofit.Builder()
            .baseUrl(" https://api.nytimes.com/svc/books/v3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val service: NYTServices = initRetrofit().create(NYTServices::class.java)

}