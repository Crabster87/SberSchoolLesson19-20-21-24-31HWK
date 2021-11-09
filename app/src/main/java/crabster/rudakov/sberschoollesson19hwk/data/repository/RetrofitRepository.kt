package crabster.rudakov.sberschoollesson19hwk.data.repository

import crabster.rudakov.sberschoollesson19hwk.data.api.RetrofitClient
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryInfo
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryItem
import io.reactivex.Single

/**
 * Класс, реализующий логику поведения клиента по получению данных
 * от сервера с помощью сторонней библиотеки 'Retrofit2'
 * */
class RetrofitRepository {

    /**
     * Метод получает страну из списка по её названию, используя
     * аттрибут "country", предоставляемого JSON-файла
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

}