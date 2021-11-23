package crabster.rudakov.sberschoollesson19hwk.ui.list.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryItem
import crabster.rudakov.sberschoollesson19hwk.data.repository.RetrofitRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Класс View Model 1-ого экрана
 * */
class ListViewModel
@Inject constructor(private val retrofitRepository: RetrofitRepository) : ViewModel() {

    private var countryList: MutableLiveData<List<CountryItem>> = MutableLiveData()
    private var exception: MutableLiveData<String> = MutableLiveData()

    /**
     * Метод возвращает список стран
     *
     * @return LiveData<List<CountryItem>>
     * */
    fun countryList(): LiveData<List<CountryItem>> {
        return countryList
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
     * Метод единожды возвращает список 'List<CountryItem>',
     * обрабатывая исключения
     * */
    @SuppressLint("CheckResult")
    fun getCountryList() {
        retrofitRepository.getCountryList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                countryList.value = it
            }, {
                exception.value = it.toString()
            })
    }

}