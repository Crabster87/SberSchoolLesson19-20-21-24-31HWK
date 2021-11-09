package crabster.rudakov.sberschoollesson19hwk.ui.country.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import crabster.rudakov.sberschoollesson19hwk.R
import crabster.rudakov.sberschoollesson19hwk.ui.main.factory.ViewModelFactory
import crabster.rudakov.sberschoollesson19hwk.ui.country.viewModel.CountryViewModel
import crabster.rudakov.sberschoollesson19hwk.ui.main.viewModel.MainViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_country.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Класс, хранящий логику отобржаения данных на 2-ом экране,
 * реализованном в виде фрагмента
 * */
class CountryFragment : DaggerFragment(), OnMapReadyCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(MainViewModel::class.java)
    }
    private val countryViewModel: CountryViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(CountryViewModel::class.java)
    }

    /**
     * Метод создаёт View фрагмента
     *
     * @param inflater объект, создающий View из XML-Layout
     * @param container контейнер фрагмента
     * @param savedInstanceState ассоциативный массив-хранилище данных
     * @return View
     * */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_country, container, false)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        /**
         * Получение данных у ViewModel по названию страны
         * @sample 'https://travelbriefing.org/Netherlands' -> Netherlands
         * */
        setObservers()

        return view
    }

    private fun setObservers() {
        mainViewModel.selectedCountry().observe(viewLifecycleOwner, {
            lifecycleScope.launch {
                countryViewModel.getCountry(it.url.split("/")[3])
            }
        })

        countryViewModel.countryInfo().observe(viewLifecycleOwner, {
            country_name.text = it.names.name
            country_full_name.text = it.names.full
            country_lat.text = it.maps.lat.toString()
            country_long.text = it.maps.long.toString()
            country_currency.text = it.currency.name

            if (it.languageList?.isNotEmpty() == true)
                country_language.text = it.languageList.get(0).language
            else country_language.text = "------"

            if (it.neighborsList?.isNotEmpty() == true)
                country_neighbors.text =
                    it.neighborsList.joinToString { it1 -> it1.name }
            else country_neighbors.text = "------"
        })

        countryViewModel.exception().observe(
            viewLifecycleOwner,
            {
                mainViewModel.setException(it)
            }
        )
    }

    /**
     * Метод получает координаты у ViewModel и устанавливает их
     * на карте во фрагменте 'SupportMapFragment', отрисовывая
     * маркер по заданным значениям. В качестве наблюдателя подписывается
     * специальный объект 'viewLifecycleOwner', имеющий данные о lifeCycle
     * View фрагмента.
     * Метод moveCamera() моментально перемещает камеру на маркер
     *
     * @param googleMap объект GoogleMap
     * */
    @SuppressLint("CheckResult")
    override fun onMapReady(googleMap: GoogleMap?) {
        countryViewModel.coordinates().observe(viewLifecycleOwner, {
            googleMap?.apply {
                val cord = LatLng(it[0].toDouble(), it[1].toDouble())
                clear()
                addMarker(
                    MarkerOptions()
                        .position(cord)
                        .title("Marker in Sydney")
                )
                moveCamera(CameraUpdateFactory.newLatLng(cord))
            }
        })
    }

}