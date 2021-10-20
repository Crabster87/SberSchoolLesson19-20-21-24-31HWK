package crabster.rudakov.sberschoollesson19hwk.ui.main.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import crabster.rudakov.sberschoollesson19hwk.utils.Constants.Factory_Exception

/**
 * Класс-фабрика, создающий ViewModel экрана главной Activity
 * */
class MainViewModelFactory(private val application: Application?): ViewModelProvider.Factory{

    /**
     * Метод создаёт и возвращает ViewModel
     *
     * @param modelClass класс, описывающий ViewModel
     * @throws IllegalArgumentException
     * @return объект MainViewModel()
     * */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return application?.let { MainViewModel(it) } as T
        }
        throw IllegalArgumentException (Factory_Exception)
    }

}