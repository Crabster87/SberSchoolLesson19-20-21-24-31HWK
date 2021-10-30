package crabster.rudakov.sberschoollesson19hwk

import crabster.rudakov.sberschoollesson19hwk.data.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Класс, запускающий приложения с целью внедрения зависимостей
 * с помощью Dagger2 перед стартом главной Activity
 * */
class App : DaggerApplication() {

    /**
     * Метод создаёт Component из класса AppComponent
     * */
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

}