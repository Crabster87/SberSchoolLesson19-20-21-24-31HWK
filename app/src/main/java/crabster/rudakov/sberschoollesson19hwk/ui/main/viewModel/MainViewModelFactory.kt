package crabster.rudakov.sberschoollesson19hwk.ui.main.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(private val application: Application?): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return application?.let { MainViewModel(it) } as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }

}