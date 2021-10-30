package crabster.rudakov.sberschoollesson19hwk.data.di.module

import crabster.rudakov.sberschoollesson19hwk.ui.country.view.CountryFragment
import crabster.rudakov.sberschoollesson19hwk.ui.list.view.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Класс, описывающий возможные используемые Fragment
 * */
@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    /**
     * Генерируется Injector для ListFragment
     * */
    @ContributesAndroidInjector
    abstract fun contributeListFragment(): ListFragment

    /**
     * Генерируется Injector для CountryFragment
     * */
    @ContributesAndroidInjector
    abstract fun contributeCountryFragment(): CountryFragment

}