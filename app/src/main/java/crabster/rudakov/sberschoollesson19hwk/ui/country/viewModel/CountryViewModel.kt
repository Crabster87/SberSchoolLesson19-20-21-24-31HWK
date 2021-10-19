package crabster.rudakov.sberschoollesson19hwk.ui.country.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import crabster.rudakov.sberschoollesson19hwk.data.api.RetrofitApi
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryInfo
import io.reactivex.Single
import kotlinx.coroutines.launch
import retrofit2.Response

class CountryViewModel(application: Application) : AndroidViewModel(application) {

    var retrofitApi: RetrofitApi = RetrofitApi()
    private val coordinates: MutableLiveData<List<Float>> = MutableLiveData()

    fun coordinates(): MutableLiveData<List<Float>> {
        return coordinates
    }

    fun setCoordinates( coordinates: List<Float>){
        this.coordinates.value = coordinates
    }

    fun getCountry(country: String): Single<Response<CountryInfo>> {
        return Single.create{ subscriber ->
            viewModelScope.launch {
                try {
                    subscriber.onSuccess(retrofitApi.getCountry(country))
                } catch (e: Exception) {
                    subscriber.onError(
                        Throwable(e.message.toString())
                    )
                }

            }
        }
    }

}