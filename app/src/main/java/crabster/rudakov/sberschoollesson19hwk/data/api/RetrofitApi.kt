package crabster.rudakov.sberschoollesson19hwk.data.api

import crabster.rudakov.sberschoollesson19hwk.data.model.CountryInfo
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryItem
import retrofit2.Response

/**
 * Класс, реализующий логику поведения клиента по получению данных
 * от сервера с помощью сторонней библиотеки 'Retrofit2'
 * */
class RetrofitApi {

    /**
     * Метод получает список стран по указанному API
     *
     * @return список стран мира
     * */
    suspend fun getCountryList(): Response<List<CountryItem>> {
        return RetrofitClient.api.getCountryList()
    }

    /**
     * Метод получает страну из списка по её названию, используя
     * аттрибут "country", предоставляемого JSON-файла
     *
     * @param country название страны
     * @return название страны
     * */
    suspend fun getCountry(country: String): Response<CountryInfo> {
        return RetrofitClient.api.getCountry(country)
    }

}