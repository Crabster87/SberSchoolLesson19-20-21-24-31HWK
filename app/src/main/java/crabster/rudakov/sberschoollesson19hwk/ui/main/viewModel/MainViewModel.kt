package crabster.rudakov.sberschoollesson19hwk.ui.main.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryItem

/**
 * Класс View Model экрана главной Activity
 * */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var progress: MutableLiveData<Boolean> = MutableLiveData()
    private var countryList: MutableLiveData<List<CountryItem>> = MutableLiveData()
    private var selectedCountry: MutableLiveData<CountryItem> = MutableLiveData()
    private var exception: MutableLiveData<String> = MutableLiveData()

    /**
     * Метод возвращает прогресс процесса завершения загрузки
     *
     * @return MutableLiveData<Boolean> boolean значение статуса
     * */
    fun progress(): MutableLiveData<Boolean> {
        return progress
    }

    /**
     * Метод устанавливает прогресс процесса завершения загрузки
     *
     * @param progress boolean значение статуса
     * */
    fun setProgress(progress: Boolean) {
        this.progress.value = progress
    }

    /**
     * Метод возвращает список стран
     *
     * @return MutableLiveData<List<CountryItem>>
     * */
    fun countryList(): MutableLiveData<List<CountryItem>> {
        return countryList
    }

    /**
     * Метод устанавливает список стран
     *
     * @param countryList список стран
     * */
    fun setCountryList(countryList: List<CountryItem>) {
        this.countryList.value = countryList
    }

    /**
     * Метод возвращает выбранную страну
     *
     * @return MutableLiveData<CountryItem>
     * */
    fun selectedCountry(): MutableLiveData<CountryItem> {
        return selectedCountry
    }

    /**
     * Метод устанавливает выбранную страну
     *
     * @param selectedCountry название страны
     * */
    fun setSelectedCountry(selectedCountry: Int) {
        this.selectedCountry.value = countryList.value?.get(selectedCountry)
    }

    /**
     * Метод возвращает строковое описание Exception
     *
     * @return MutableLiveData<String>
     * */
    fun exception(): MutableLiveData<String> {
        return exception
    }

    /**
     * Метод устанавливает строковое описание Exception
     *
     * @param exception строковое описание Exception
     * */
    fun setException(exception: String) {
        this.exception.value = exception
    }

}