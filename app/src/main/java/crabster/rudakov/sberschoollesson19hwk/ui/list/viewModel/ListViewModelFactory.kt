package crabster.rudakov.sberschoollesson19hwk.ui.list.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListViewModelFactory(private val application: Application?): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListViewModel::class.java)){
            return application?.let { ListViewModel(it) } as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }

}