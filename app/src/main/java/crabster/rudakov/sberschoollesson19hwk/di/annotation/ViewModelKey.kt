package crabster.rudakov.sberschoollesson19hwk.di.annotation

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Собственная аннотация для методов, ассоциирующая ключи в Map
 * и классы View моделей(наследников класса ViewModel)
 * */
@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ViewModelKey(val value: KClass<out ViewModel>)