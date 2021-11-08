package crabster.rudakov.sberschoollesson19hwk.ui.main.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import crabster.rudakov.sberschoollesson19hwk.R
import crabster.rudakov.sberschoollesson19hwk.ui.main.factory.ViewModelFactory
import crabster.rudakov.sberschoollesson19hwk.ui.main.viewModel.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Класс Activity главного экрана, наследуется от DaggerAppCompatActivity
 * с целью отслеживания кода, который генерирует Dagger2
 * */
class MainActivity : DaggerAppCompatActivity() {

    lateinit var navController: NavController

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }


    /**
     * Метод создаёт View активити, а также создаёт 'ViewModel' и навигацию
     * между фрагментами
     *
     * @param savedInstanceState ассоциативный массив-хранилище данных
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        displayException()
    }

    /**
     * Метод наблюдает за возникновением Exception во 'ViewModel' и отправляет
     * Toast-message в случае его возникновения
     * */
    private fun displayException() {
        mainViewModel.exception().observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

}