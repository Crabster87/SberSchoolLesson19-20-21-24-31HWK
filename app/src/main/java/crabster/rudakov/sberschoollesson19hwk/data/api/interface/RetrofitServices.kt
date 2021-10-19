package crabster.rudakov.sberschoollesson19hwk.data.api.`interface`

import crabster.rudakov.sberschoollesson19hwk.data.model.CountryInfo
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryItem
import crabster.rudakov.sberschoollesson19hwk.utils.Constants.LIST_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitServices {

    @GET(LIST_URL)
    suspend fun getCountryList(): Response<List<CountryItem>>

    @GET("{country}")
    suspend fun getCountry(@Path("country") country: String): Response<CountryInfo>

}