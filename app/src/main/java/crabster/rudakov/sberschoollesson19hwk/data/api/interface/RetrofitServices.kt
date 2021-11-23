package crabster.rudakov.sberschoollesson19hwk.data.api.`interface`

import crabster.rudakov.sberschoollesson19hwk.data.model.CountryInfo
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryItem
import crabster.rudakov.sberschoollesson19hwk.data.model.ImageList
import crabster.rudakov.sberschoollesson19hwk.utils.Constants
import crabster.rudakov.sberschoollesson19hwk.utils.Constants.LIST_URL
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
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
    fun getCountryList(): Single<List<CountryItem>>

    /**
     * Метод получает страну из списка по её названию, используя
     * аттрибут "country" предоставляемого JSON-файла
     *
     * @param country название страны
     * @return название страны
     * */
    @GET("{country}")
    fun getCountry(@Path("country") country: String): Single<CountryInfo>

    /**
     * Метод получает флаг страны из списка по его аббревиатуре, используя
     * аттрибут "flag" предоставляемого JSON-файла
     *
     * @param flag флаг страны
     * @return флаг страны
     * */
    @Headers("Content-Type: image/svg+xml")
    @GET(Constants.FLAG_URL + "{flag}" + Constants.FLAG_FORMAT)
    fun getFlag(@Path("flag") flag: String): Single<String>

    /**
     * Метод получает список URL картинок, который выдаётся в результате
     * поискового запроса по названию страны на сайте https://pixabay.com/
     *
     * @param key ключ пользователя
     * @param order название страны
     * @return список URL изображений
     * */
    @GET(Constants.IMAGES_API)
    fun getImages(@Query("key") key: String, @Query("q") order: String): Single<ImageList>

}