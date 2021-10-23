package crabster.rudakov.sberschoollesson19hwk

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryItem
import crabster.rudakov.sberschoollesson19hwk.ui.list.viewModel.ListViewModel
import crabster.rudakov.sberschoollesson19hwk.ui.main.viewModel.MainViewModel
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito
import java.util.concurrent.Callable

class ExampleUnitTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val app: Application = Mockito.mock(Application::class.java)
    private val listViewModel = ListViewModel(app)
    private val mainViewModel = MainViewModel(app)

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> Schedulers.trampoline() }
    }

    /**
     * Проверяется возможность получения списка стран при подключении
     * к сети
     * */
    @Test
    fun testConnection() {
        listViewModel.getCountryList()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                assert(it.isSuccessful)
            }, {
                it.message?.let { ex -> println(ex) }
                assert(false)
            })
    }

    /**
     * Проверяется корректность работы метода, изменяющего состяние
     * прогресса процесса завершения загрузки в 'MainViewModel'
     * */
    @Test
    fun testProgress() {
        mainViewModel.setProgress(true)
        val progress = mainViewModel.progress().value
        println("$progress")
        assert(mainViewModel.progress().value == true)
    }

    /**
     * Проверяется корректность работы метода, устанавливающего список
     * стран во 'MainViewModel'
     * */
    @Test
    fun testCountryList() {
        val list = listOf(CountryItem("test1", "test1"), CountryItem("test2", "test2"))
        mainViewModel.setCountryList(list)
        println("$list")
        assert(mainViewModel.countryList().value?.get(0)?.name == "test1")
    }

    /**
     * Проверяется корректность работы метода, устанавливающего выбранную
     * страну во 'MainViewModel'
     * */
    @Test
    fun testSelectedCountry() {
        val list = listOf(CountryItem("test1", "test1"), CountryItem("test2", "test2"))
        mainViewModel.setCountryList(list)
        mainViewModel.setSelectedCountry(0)
        val country = mainViewModel.selectedCountry().value
        println("$country")
        assert(country?.name == "test1")
    }

}