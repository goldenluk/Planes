package com.goldenluk.planes.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {

    companion object {

        fun provideRetrofit() : Retrofit {
            val httpClient = OkHttpClient.Builder().build()
            return Retrofit.Builder()
                .baseUrl("https://yasen.hotellook.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
        }
     }
}