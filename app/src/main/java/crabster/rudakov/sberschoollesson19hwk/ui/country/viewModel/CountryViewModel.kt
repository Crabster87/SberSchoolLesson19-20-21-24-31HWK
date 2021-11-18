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
    private val flag: MutableLiveData<String> = MutableLiveData()

    /**
     * Метод возвращает объект, хранящий информацию о стране
     *
     * @return LiveData<CountryInfo>
     * */
    fun countryInfo(): LiveData<CountryInfo> {
        return countryInfo
    }

    /**
     * Метод возвращает строковое представление исключения
     *
     * @return LiveData<String>
     * */
    fun exception(): LiveData<String> {
        return exception
    }

    /**
     * Метод возвращает флаг
     *
     * @return LiveData<String>
     * */
    fun flag(): LiveData<String> {
        return flag
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
     * переданному названию страны, обрабатывая исключения.
     *
     * @param country название страны
     * @return Single<CountryInfo>
     * */
    @SuppressLint("CheckResult")
    fun getCountry(country: String) {
        countryInfo.value = null
        retrofitRepository.getCountry(country)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                countryInfo.value = it
                setCoordinates(
                    listOf(
                        it?.maps?.lat,
                        it?.maps?.long
                    ) as List<Float>
                )
            }, {
                exception.value = it.toString()
            })
    }

    /**
     * Метод единожды возвращает объект 'Single<String>', полученный по
     * переданной аббревиатуре флага, обрабатывая исключения.
     *
     * @param flag аббревиатура флага
     * @return Single<String>
     * */
    @SuppressLint("CheckResult")
    fun getFlag(flag: String) {
        retrofitRepository.getFlag(flag)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                this.flag.value = it
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