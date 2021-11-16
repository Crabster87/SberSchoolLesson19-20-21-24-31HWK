package crabster.rudakov.sberschoollesson19hwk.ui.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryItem
import javax.inject.Inject

/**
 * Класс View Model экрана главной Activity
 * */
class MainViewModel
@Inject constructor() : ViewModel() {

    private var progress: MutableLiveData<Boolean> = MutableLiveData()
    private var countryList: MutableLiveData<List<CountryItem>> = MutableLiveData()
    private var selectedCountry: MutableLiveData<String> = MutableLiveData()
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
     * @return MutableLiveData<String>
     * */
    fun selectedCountry(): MutableLiveData<String> {
        return selectedCountry
    }

    /**
     * Метод устанавливает выбранную страну
     *
     * @param selectedCountry название страны
     * */
    fun setSelectedCountry(selectedCountry: String) {
        this.selectedCountry.value = selectedCountry
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