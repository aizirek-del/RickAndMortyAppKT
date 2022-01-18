package com.example.rickandmortyapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


object RetrofitModule {

    private val retrofit by lazy {

    Retrofit.Builder()
                .baseUrl(" https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient)
                .build()

        }
       val api: Api by lazy {
           retrofit.create(Api::class.java)
       }

    private val okhttpClient: OkHttpClient by lazy {
        // Add the interceptor to OkHttpClient
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val builder = OkHttpClient.Builder()
        builder.interceptors().add(interceptor)
        builder.build()
    }
}
