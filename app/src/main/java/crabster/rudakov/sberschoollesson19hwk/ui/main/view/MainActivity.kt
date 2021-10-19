package crabster.rudakov.sberschoollesson19hwk.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import crabster.rudakov.sberschoollesson19hwk.R
import crabster.rudakov.sberschoollesson19hwk.ui.main.viewModel.MainViewModel
import crabster.rudakov.sberschoollesson19hwk.ui.main.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        val factory = MainViewModelFactory(application)
        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        displayException()
    }

    private fun displayException() {
        mainViewModel.exception().observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

}