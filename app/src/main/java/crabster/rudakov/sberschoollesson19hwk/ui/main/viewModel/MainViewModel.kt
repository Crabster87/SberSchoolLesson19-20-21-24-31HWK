package crabster.rudakov.sberschoollesson19hwk.ui.main.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryItem

class MainViewModel(application: Application) : AndroidViewModel(application)  {

    private var progress: MutableLiveData<Boolean> = MutableLiveData()
    private var countryList: MutableLiveData<List<CountryItem>> = MutableLiveData()
    private var selectedCountry: MutableLiveData<CountryItem> = MutableLiveData()
    private var exception: MutableLiveData<String> = MutableLiveData()

    fun progress(): MutableLiveData<Boolean>{
        return progress
    }
    fun setProgress(progress: Boolean) {
        this.progress.value = progress
    }
    fun countryList(): MutableLiveData<List<CountryItem>> {
        return countryList
    }
    fun setCountryList(countryList: List<CountryItem>) {
        this.countryList.value = countryList
    }
    fun selectedCountry(): MutableLiveData<CountryItem>{
        return selectedCountry
    }
    fun setSelectedCountry(selectedCountry: Int) {
        this.selectedCountry.value = countryList.value?.get(selectedCountry)
    }
    fun exception(): MutableLiveData<String>{
        return exception
    }
    fun setException(exception: String) {
        this.exception.value = exception
    }

}