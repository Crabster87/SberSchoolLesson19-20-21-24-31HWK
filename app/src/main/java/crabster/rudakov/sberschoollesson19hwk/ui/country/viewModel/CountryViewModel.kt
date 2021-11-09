package crabster.rudakov.sberschoollesson19hwk.ui.country.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryInfo
import crabster.rudakov.sberschoollesson19hwk.data.repository.RetrofitRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Класс View Model 2-ого экрана
 * */
class CountryViewModel
@Inject constructor() : ViewModel() {

    private var retrofitRepository = RetrofitRepository()
    private var countryInfo: MutableLiveData<CountryInfo> = MutableLiveData()
    private var exception: MutableLiveData<String> = MutableLiveData()
    private val coordinates: MutableLiveData<List<Float>> = MutableLiveData()

    fun countryInfo(): MutableLiveData<CountryInfo> {
        return countryInfo
    }
    fun exception(): MutableLiveData<String> {
        return exception
    }


    /**
     * Метод возвращает объект 'LiveData'
     *
     * @return LiveData<List<Float>>
     * */
    fun coordinates(): LiveData<List<Float>> {
        return coordinates
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
    @SuppressLint("CheckResult")
    fun getCountry(country: String) {
        retrofitRepository.getCountry(country)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                countryInfo.value = it
                setCoordinates(listOf(
                    it?.maps?.lat,
                    it?.maps?.long
                ) as List<Float>)
            }, {
                exception.value = it.toString()
            })
    }

    /**
     * Метод устанавливает переданный список со значениями широты
     * и долготы в объект 'LiveData'
     *
     * @param coordinates список из 2-х элементов(широта и долгота)
     * */
    private fun setCoordinates(coordinates: List<Float>) {
        this.coordinates.value = coordinates
    }

}