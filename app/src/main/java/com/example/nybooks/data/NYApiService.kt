package com.example.nybooks.data

import com.example.nybooks.data.services.NYTServices
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NYApiService {

    val service: NYTServices
        get() = retrofit.create(
            NYTServices::class.java)
    private val interceptorLog: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://api.nytimes.com/svc/")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .create()
                )
            )
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(NYAuthInterceptor())
                    .addInterceptor(interceptorLog)
                    .authenticator(NYAuthenticator())
                    .build()
            )
            .build()
    }

    class NYAuthInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val url = request.url.newBuilder()
                .addQueryParameter("api-key", UserStorage.instance.token)
                .build()
            return chain.proceed(request.newBuilder().url(url).build())
        }
    }
}