package crabster.rudakov.sberschoollesson19hwk

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryItem
import crabster.rudakov.sberschoollesson19hwk.data.repository.RetrofitRepository
import crabster.rudakov.sberschoollesson19hwk.ui.country.viewModel.CountryViewModel
import crabster.rudakov.sberschoollesson19hwk.ui.list.viewModel.ListViewModel
import crabster.rudakov.sberschoollesson19hwk.ui.main.viewModel.MainViewModel
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Test

import org.junit.Before
import org.junit.Rule

class ExampleUnitTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val retrofitRepository = RetrofitRepository()
    private val countryViewModel = CountryViewModel(retrofitRepository)
    private val listViewModel = ListViewModel(retrofitRepository)
    private val mainViewModel = MainViewModel()

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    /**
     * Проверяется возможность получения списка стран при подключении
     * к сети
     * */
    @Test
    fun testConnection() {
        listViewModel.getCountryList()
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
        mainViewModel.setSelectedCountry(list[0].url)
        val country = mainViewModel.selectedCountry()
        println("$country")
        assert(country.value == "test1")
    }

    @Test
    fun testSetCountryInfo() {
        listViewModel.getCountryList()
        Thread.sleep(3000)
        mainViewModel.setCountryList(listViewModel.countryList().value!!)
        val selectedCountry = listViewModel.countryList().value!![0]
        println(selectedCountry.name)
        mainViewModel.selectedCountry().value = selectedCountry.url
        countryViewModel.getCountry(selectedCountry.url.split("/")[3])
        Thread.sleep(3000)
        val country = countryViewModel.countryInfo()
        println("${country.value}")
        assert(country.value?.names?.name == selectedCountry.name)
    }


    @Test
    fun testGetFlag() {
        countryViewModel.getFlag("ru")
        Thread.sleep(3000)
        val flag = countryViewModel.flag().value
        println("$flag")
        assert(flag != null)
    }

    @Test
    fun testGetImages() {
        countryViewModel.getImages("russia")
        Thread.sleep(3000)
        val images = countryViewModel.images().value
        println("$images")
        assert(images != null)
    }

    @Test
    fun testBreakFlag() {
        countryViewModel.getFlag("ERRORERROR")
        Thread.sleep(3000)
        val flag = countryViewModel.flag().value
        println("$flag")
        flag?.contains("html")?.let { assert(it) }
    }

    @Test
    fun testBreakImages() {
        countryViewModel.getFlag("ERRORERROR")
        Thread.sleep(3000)
        val images = countryViewModel.flag().value
        println("$images")
        images?.contains("html")?.let { assert(it) }
    }

}