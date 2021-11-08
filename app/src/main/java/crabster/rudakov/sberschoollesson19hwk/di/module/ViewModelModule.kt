package crabster.rudakov.sberschoollesson19hwk.di.module

import androidx.lifecycle.ViewModel
import crabster.rudakov.sberschoollesson19hwk.di.annotation.ViewModelKey
import crabster.rudakov.sberschoollesson19hwk.ui.country.viewModel.CountryViewModel
import crabster.rudakov.sberschoollesson19hwk.ui.list.viewModel.ListViewModel
import crabster.rudakov.sberschoollesson19hwk.ui.main.viewModel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Класс, описывающий возможные используемые ViewModel
 * */
@Suppress("unused")
@Module
abstract class ViewModelModule {

    /**
     * Метод привязывает MainViewModel к Map<Class<out ViewModel>
     * с ключом по названию класса
     * */
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    /**
     * Метод привязывает ListViewModel к Map<Class<out ViewModel>
     * с ключом по названию класса
     * */
    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(listViewModel: ListViewModel): ViewModel

    /**
     * Метод привязывает CountryViewModel к Map<Class<out ViewModel>
     * с ключом по названию класса
     * */
    @Binds
    @IntoMap
    @ViewModelKey(CountryViewModel::class)
    abstract fun bindCountryViewModel(countryViewModel: CountryViewModel): ViewModel

}