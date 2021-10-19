package crabster.rudakov.sberschoollesson19hwk.ui.list.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import crabster.rudakov.sberschoollesson19hwk.data.api.RetrofitApi
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryItem
import io.reactivex.Single
import kotlinx.coroutines.launch
import retrofit2.Response

class ListViewModel(application: Application) : AndroidViewModel(application) {

    var retrofitApi: RetrofitApi = RetrofitApi()

    fun getCountryList(): Single<Response<List<CountryItem>>>{
        return Single.create{ subscriber ->
            viewModelScope.launch {
                try {
                    subscriber.onSuccess(retrofitApi.getCountryList())
                } catch (e: Exception) {
                    subscriber.onError(
                        Throwable(e.message.toString())
                    )
                }

            }
        }
    }

}