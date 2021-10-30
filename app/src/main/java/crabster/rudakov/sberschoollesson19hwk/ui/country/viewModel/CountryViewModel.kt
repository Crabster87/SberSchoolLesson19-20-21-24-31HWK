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
import javax.inject.Inject

/**
 * Класс View Model 2-ого экрана
 * */
class CountryViewModel
@Inject constructor(application: Application) : AndroidViewModel(application) {

    var retrofitApi: RetrofitApi = RetrofitApi()
    private val coordinates: MutableLiveData<List<Float>> = MutableLiveData()

    /**
     * Метод возвращает объект 'LiveData'
     *
     * @return MutableLiveData<List<Float>>
     * */
    fun coordinates(): MutableLiveData<List<Float>> {
        return coordinates
    }

    /**
     * Метод устанавливает переданный список со значениями широты
     * и долготы в объект 'LiveData'
     *
     * @param coordinates список из 2-х элементов(широта и долгота)
     * */
    fun setCoordinates(coordinates: List<Float>) {
        this.coordinates.value = coordinates
    }

    /**
     * Метод единожды возвращает объект 'CountryInfo', полученный по
     * переданному названию страны, для асинхронной обработки кода
     * используя при этом Kotlin Coroutines и обрабатывая исключения.
     * Объект 'viewModelScope' отменяет привязку 'CoroutineScope'
     * после уничтожения ViewModel
     *
     * @param country название страны
     * @return Single<Response<CountryInfo>>
     * */
    fun getCountry(country: String): Single<Response<CountryInfo>> {
        return Single.create { subscriber ->
            viewModelScope.launch {
                try {
                    subscriber.onSuccess(retrofitApi.getCountry(country))
                } catch (e: Exception) {
                    subscriber.onError(Throwable(e.message.toString()))
                }
            }
        }
    }

}