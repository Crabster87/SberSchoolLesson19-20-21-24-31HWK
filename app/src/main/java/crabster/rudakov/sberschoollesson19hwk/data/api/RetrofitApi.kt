package crabster.rudakov.sberschoollesson19hwk.data.api

import crabster.rudakov.sberschoollesson19hwk.data.model.CountryInfo
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryItem
import retrofit2.Response

class RetrofitApi {

    suspend fun getCountryList(): Response<List<CountryItem>> {
        return RetrofitClient.api.getCountryList()
    }

    suspend fun getCountry(country: String): Response<CountryInfo> {
        return RetrofitClient.api.getCountry(country)
    }

}