package crabster.rudakov.sberschoollesson19hwk.data.api.`interface`

import crabster.rudakov.sberschoollesson19hwk.data.model.CountryInfo
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryItem
import crabster.rudakov.sberschoollesson19hwk.utils.Constants.LIST_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

/**
 * Интерфейс, описывающий взаимодействие приложения(клиента) и API(сервера)
 * */
@Singleton
interface RetrofitServices {

    /**
     * Метод получает список стран по указанному API
     *
     * @return список стран мира
     * */
    @GET(LIST_URL)
    suspend fun getCountryList(): Response<List<CountryItem>>

    /**
     * Метод получает страну из списка по её названию, используя
     * аттрибут "country", предоставляемого JSON-файла
     *
     * @param country название страны
     * @return название страны
     * */
    @GET("{country}")
    suspend fun getCountry(@Path("country") country: String): Response<CountryInfo>

}