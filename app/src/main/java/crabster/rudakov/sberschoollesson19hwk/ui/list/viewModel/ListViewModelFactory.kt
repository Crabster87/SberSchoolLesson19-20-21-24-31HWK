package crabster.rudakov.sberschoollesson19hwk.ui.list.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import crabster.rudakov.sberschoollesson19hwk.utils.Constants.Factory_Exception

/**
 * Класс-фабрика, создающий ViewModel 1-ого экрана приложения
 * */
class ListViewModelFactory(private val application: Application?) : ViewModelProvider.Factory {

    /**
     * Метод создаёт и возвращает ViewModel
     *
     * @param modelClass класс, описывающий ViewModel
     * @throws IllegalArgumentException
     * @return объект ListViewModel()
     * */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return application?.let { ListViewModel(it) } as T
        }
        throw IllegalArgumentException(Factory_Exception)
    }

}