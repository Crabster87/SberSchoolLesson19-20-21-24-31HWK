package crabster.rudakov.sberschoollesson19hwk.ui.country.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import crabster.rudakov.sberschoollesson19hwk.utils.Constants.Factory_Exception

/**
 * Класс-фабрика, создающий ViewModel 2-ого экрана приложения
 * */
class CountryViewModelFactory(val application: Application) : ViewModelProvider.Factory {

    /**
     * Метод создаёт и возвращает ViewModel
     *
     * @param modelClass класс, описывающий ViewModel
     * @throws IllegalArgumentException
     * @return объект CountryViewModel()
     * */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            return CountryViewModel(application) as T
        }
        throw IllegalArgumentException(Factory_Exception)
    }

}