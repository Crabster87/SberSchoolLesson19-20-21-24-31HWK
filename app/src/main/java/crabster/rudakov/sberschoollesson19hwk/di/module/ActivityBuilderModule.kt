package crabster.rudakov.sberschoollesson19hwk.di.module

import crabster.rudakov.sberschoollesson19hwk.ui.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Класс, описывающий зависимости, используемые в Activity
 * */
@Suppress("unused")
@Module
abstract class ActivityBuilderModule {

    /**
     * Генерируется Injector для соответствующих типов
     * */
    @ContributesAndroidInjector(
        modules = [
            ViewModelModule::class,
            FragmentBuildersModule::class,
        ]
    )

    /**
     * Данные модулей передаются в главный Component Dagger2 (AppComponent)
     * */
    abstract fun contributeMainActivity(): MainActivity

}