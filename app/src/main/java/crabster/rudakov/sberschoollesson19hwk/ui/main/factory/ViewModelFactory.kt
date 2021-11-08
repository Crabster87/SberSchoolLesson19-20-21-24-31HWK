package crabster.rudakov.sberschoollesson19hwk.ui.main.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * Класс-фабрика, создающий ViewModel
 * */
class ViewModelFactory
@Inject constructor(private val viewModelMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    /**
     * Метод создаёт и возвращает ViewModel
     *
     * @param modelClass класс, описывающий ViewModel
     * @return ViewModel, извлечённая из Map по названию класса
     * */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModelMap[modelClass]!!.get() as T
    }

}