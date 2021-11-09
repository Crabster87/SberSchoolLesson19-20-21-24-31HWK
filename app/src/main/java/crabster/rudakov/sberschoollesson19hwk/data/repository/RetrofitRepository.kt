package crabster.rudakov.sberschoollesson19hwk.data.repository

import crabster.rudakov.sberschoollesson19hwk.data.api.RetrofitClient
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryInfo
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryItem
import io.reactivex.Single

class RetrofitRepository {

    fun getCountry(country: String): Single<CountryInfo> {
        return RetrofitClient.api.getCountry(country)
    }

    fun getCountryList(): Single<List<CountryItem>> {
        return RetrofitClient.api.getCountryList()
    }

}