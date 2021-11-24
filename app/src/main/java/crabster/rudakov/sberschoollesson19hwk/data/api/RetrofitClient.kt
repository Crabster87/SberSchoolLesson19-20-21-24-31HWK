package crabster.rudakov.sberschoollesson19hwk.data.api

import com.google.gson.GsonBuilder
import crabster.rudakov.sberschoollesson19hwk.data.api.`interface`.RetrofitServices
import crabster.rudakov.sberschoollesson19hwk.utils.Constants
import crabster.rudakov.sberschoollesson19hwk.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Singleton объект, имеющий возможность подключения к указанным
 * внешним API и наделённый функционалом по работе с полученными
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
     * Для работы с запросами к API 'https://travelbriefing.org/'(получение
     * данных по странам) создаётся объект класса 'Retrofit', принимающий
     * заданный 'URL' и создающий фабрики для получения примитивов(строки)
     * из запроса, для сериализации/десериализации объектов, для получения
     * сущностей RxJava(Observable, Flowable, Single, Completable or Maybe)
     * */
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    /**
     * Для работы с запросами к API 'https://pixabay.com/'(загрузка изображений)
     * создаётся объект класса 'Retrofit', принимающий заданный 'URL' и создающий
     * фабрики для сериализации/десериализации объектов, для получения сущностей
     * RxJava(Observable, Flowable, Single, Completable or Maybe)
     * */
    private val retrofitImages = Retrofit.Builder()
        .baseUrl(Constants.IMAGES_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    /**
     * Создаётся объект для работы с запросами к API 'https://travelbriefing.org/'
     * (получение данных по странам), реализующий интерфейс 'RetrofitServices'
     * */
    val api: RetrofitServices by lazy {
        retrofit.create(RetrofitServices::class.java)
    }

    /**
     * Создаётся объект для работы с запросами к API 'https://pixabay.com/'
     * (загрузка изображений), реализующий интерфейс 'RetrofitServices'
     * */
    val apiImage: RetrofitServices by lazy {
        retrofitImages.create(RetrofitServices::class.java)
    }

}