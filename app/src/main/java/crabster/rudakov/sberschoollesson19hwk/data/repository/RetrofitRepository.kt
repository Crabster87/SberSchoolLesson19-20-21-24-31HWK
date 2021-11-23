package crabster.rudakov.sberschoollesson19hwk.data.repository

import crabster.rudakov.sberschoollesson19hwk.data.api.RetrofitClient
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryInfo
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryItem
import crabster.rudakov.sberschoollesson19hwk.data.model.ImageList
import crabster.rudakov.sberschoollesson19hwk.utils.Constants
import io.reactivex.Single
import javax.inject.Inject

/**
 * Класс, реализующий логику поведения клиента по получению данных
 * от сервера с помощью сторонней библиотеки 'Retrofit2'
 * */
class RetrofitRepository @Inject constructor() {

    /**
     * Метод получает страну из списка по её названию, используя
     * аттрибут "country" предоставляемого JSON-файла
     *
     * @param country название страны
     * @return название страны
     * */
    fun getCountry(country: String): Single<CountryInfo> {
        return RetrofitClient.api.getCountry(country)
    }

    /**
     * Метод получает список стран по указанному API
     *
     * @return список стран мира
     * */
    fun getCountryList(): Single<List<CountryItem>> {
        return RetrofitClient.api.getCountryList()
    }

    /**
     * Метод получает флаг страны из списка по его аббревиатуре, используя
     * аттрибут "flag" предоставляемого JSON-файла
     *
     * @param flag флаг страны
     * @return флаг страны
     * */
    fun getFlag(flag: String): Single<String> {
        return RetrofitClient.api.getFlag(flag)
    }

    /**
     * Метод получает список URL картинок, который выдаётся в результате
     * поискового запроса по названию страны на сайте https://pixabay.com/
     *
     * @param country название страны
     * @return список URL изображений
     * */
    fun getImages(country: String): Single<ImageList> {
        return RetrofitClient.apiImage.getImages(Constants.IMAGES_KEY, country)
    }

}