package crabster.rudakov.sberschoollesson19hwk.data.api

import com.google.gson.GsonBuilder
import crabster.rudakov.sberschoollesson19hwk.data.api.`interface`.RetrofitServices
import crabster.rudakov.sberschoollesson19hwk.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton объект, имеющий возможность подключения к указанному
 * внешнему API и наделённый функционалом по работе с полученными
 * данными
 * */
object RetrofitClient {

    /**
     * Создаётся объект класса 'GsonBuilder' с функционалом, умеющим
     * создавать 'Gson' с различными параметрами
     * */
    private var gson = GsonBuilder()
        .setLenient()
        .create()

    /**
     * Создаётся объект класса 'Retrofit', принимающий заданный 'URL' и
     * создающий фабрику для сериализации/десериализации объектов
     * */
    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    /**
     * Создаётся объект, реализующий интерфейс 'RetrofitServices'
     * */
    val api: RetrofitServices by lazy {
        retrofit.create(RetrofitServices::class.java)
    }

}