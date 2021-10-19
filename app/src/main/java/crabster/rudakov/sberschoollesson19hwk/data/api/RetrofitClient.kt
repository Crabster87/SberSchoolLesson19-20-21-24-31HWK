package crabster.rudakov.sberschoollesson19hwk.data.api

import com.google.gson.GsonBuilder
import crabster.rudakov.sberschoollesson19hwk.data.api.`interface`.RetrofitServices
import crabster.rudakov.sberschoollesson19hwk.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    var gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val api: RetrofitServices by lazy {
        retrofit.create(RetrofitServices::class.java)
    }

}