package crabster.rudakov.sberschoollesson19hwk.ui.country.viewModel

import android.app.Application

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CountryViewModelFactory(val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            return CountryViewModel(application) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }

}