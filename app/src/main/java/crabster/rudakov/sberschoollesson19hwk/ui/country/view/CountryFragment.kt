package crabster.rudakov.sberschoollesson19hwk.ui.country.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import crabster.rudakov.sberschoollesson19hwk.R
import crabster.rudakov.sberschoollesson19hwk.ui.country.viewModel.CountryViewModel
import crabster.rudakov.sberschoollesson19hwk.ui.country.viewModel.CountryViewModelFactory
import crabster.rudakov.sberschoollesson19hwk.ui.main.view.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_country.*

/**
 * Класс, хранящий логику отобржаения данных на 2-ом экране,
 * реализованном в виде фрагмента
 * */
class CountryFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mainActivity: MainActivity
    private lateinit var countryViewModel: CountryViewModel

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
        mainActivity = context as MainActivity

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val factory = CountryViewModelFactory(mainActivity.application)
        countryViewModel = ViewModelProvider(this, factory).get(CountryViewModel::class.java)

        /**
         * Получение данных у ViewModel по названию страны
         * @sample 'https://travelbriefing.org/Netherlands' -> Netherlands
         * */
        mainActivity.mainViewModel.selectedCountry().observe(viewLifecycleOwner, {
            getCountry(it.url.split("/")[3])
        })

        return view
    }

    /**
     * Метод получает данные у ViewModel и производит наполнение всех View
     * текущего фрагмента, обрабатывая исключения
     *
     * @param country название страны
     * */
    @SuppressLint("CheckResult")
    private fun getCountry(country: String) {
        countryViewModel.getCountry(country)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isSuccessful) {
                    country_name.text = it.body()?.names?.name
                    country_full_name.text = it.body()?.names?.full
                    country_lat.text = it.body()?.maps?.lat.toString()
                    country_long.text = it.body()?.maps?.long.toString()

                    val coordinates: List<Float> = listOf(
                        it.body()?.maps?.lat,
                        it.body()?.maps?.long
                    ) as List<Float>

                    countryViewModel.setCoordinates(coordinates)

                    country_currency.text = it.body()?.currency?.name

                    if (it.body()?.languageList?.size != 0)
                        country_language.text = it.body()?.languageList?.get(0)?.language
                    else country_language.text = "------"


                    if (it.body()?.neighborsList?.size != 0)
                        country_neighbors.text =
                            it.body()?.neighborsList?.joinToString { it1 -> it1.name }
                    else country_neighbors.text = "------"

                } else {
                    mainActivity.mainViewModel.setException(it.errorBody().toString())
                }
            }, {
                it.message?.let { ex -> mainActivity.mainViewModel.setException(ex) }
            })
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