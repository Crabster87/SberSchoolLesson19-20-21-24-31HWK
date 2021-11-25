package crabster.rudakov.sberschoollesson19hwk.ui.country.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.pixplicity.sharp.Sharp
import crabster.rudakov.sberschoollesson19hwk.R
import crabster.rudakov.sberschoollesson19hwk.ui.country.adapter.ImageViewAdapter
import crabster.rudakov.sberschoollesson19hwk.ui.country.viewModel.CountryViewModel
import crabster.rudakov.sberschoollesson19hwk.ui.main.factory.ViewModelFactory
import crabster.rudakov.sberschoollesson19hwk.ui.main.viewModel.MainViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_country.*
import java.util.*
import javax.inject.Inject


/**
 * Класс, хранящий логику отображения данных на 2-ом экране,
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

        setObservers()
        return view
    }

    /**
     * Метод получает данные у CountryViewModel:
     * 1) по значению аттрибута 'name'(название страны), взятому из URL,
     * получает название, полное название, координаты, язык, список соседей,
     * координаты
     * @sample 'https://travelbriefing.org/Netherlands' -> Netherlands
     *
     * 2) по значению аттрибута 'iso2'(URL флага), взятому из URL, получает
     * изображение флага в формате SVG, устанавливая его в ImageView с
     * помощью библиотеки 'Pixplicity/sharp'
     * @sample 'https://travelbriefing.org/sites/views/default/images/flags/4x3/ru.svg' -> ru
     *
     * 3) по значению аттрибута 'name'(название страны), взятому из URL,
     * получает список изображений по стране
     *
     * Производит наполнение всех View текущего фрагмента, обрабатывая исключения
     * */
    private fun setObservers() {
        mainViewModel.selectedCountry().observe(viewLifecycleOwner) {
            countryViewModel.getCountry(it.split("/")[3])
        }

        countryViewModel.countryInfo().observe(viewLifecycleOwner) {
            if (it != null) {
                if (it.names.iso2 != null) {
                    countryViewModel.getFlag(it.names.iso2.lowercase(Locale.getDefault()))
                }
                countryViewModel.getImages(it.names.name)

                country_name.text = it.names.name
                country_full_name.text = it.names.full

                country_lat.text = it.maps.lat.toString()
                country_long.text = it.maps.long.toString()
                country_currency.text = it.currency.name

                if (it.languageList?.isNotEmpty() == true)
                    country_language.text = it.languageList[0].language
                else country_language.text = "------"

                if (it.neighborsList?.isNotEmpty() == true)
                    country_neighbors.text =
                        it.neighborsList.joinToString { it1 -> it1.name }
                else country_neighbors.text = "------"
            }
        }

        countryViewModel.flag().observe(viewLifecycleOwner) {
            try {
                Sharp.loadString(it).into(flag_image_view)
            } catch (e: IllegalArgumentException) {
                Log.d("FLAG", "DAMAGED FLAG IMAGE DETECTED")
            }
        }

        countryViewModel.images().observe(viewLifecycleOwner) {
            for (i in it) {
                image_recycler_view.layoutManager =
                    LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
                image_recycler_view.adapter = ImageViewAdapter(it)
            }
        }

        countryViewModel.exception().observe(
            viewLifecycleOwner
        ) {
            mainViewModel.setException(it)
        }
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
        countryViewModel.coordinates().observe(viewLifecycleOwner) {
            googleMap?.apply {
                val cord = LatLng(it[0].toDouble(), it[1].toDouble())
                clear()
                addMarker(
                    MarkerOptions()
                        .position(cord)
                        .title(getString(R.string.map_marker))
                )
                moveCamera(CameraUpdateFactory.newLatLng(cord))
            }
        }
    }

}