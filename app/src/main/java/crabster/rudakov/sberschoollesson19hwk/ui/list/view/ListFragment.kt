package crabster.rudakov.sberschoollesson19hwk.ui.list.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import crabster.rudakov.sberschoollesson19hwk.R
import crabster.rudakov.sberschoollesson19hwk.data.di.factory.ViewModelFactory
import crabster.rudakov.sberschoollesson19hwk.ui.list.adapter.IListItemListener
import crabster.rudakov.sberschoollesson19hwk.ui.list.adapter.ListViewAdapter
import crabster.rudakov.sberschoollesson19hwk.ui.list.viewModel.ListViewModel
import crabster.rudakov.sberschoollesson19hwk.ui.main.view.MainActivity
import crabster.rudakov.sberschoollesson19hwk.ui.main.viewModel.MainViewModel
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ListFragment : DaggerFragment(), IListItemListener {

    private val mainActivity: MainActivity by lazy {
        context as MainActivity
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(MainViewModel::class.java)
    }
    private val listViewModel: ListViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(ListViewModel::class.java)
    }

    /**
     * Метод создаёт View данного фрагмента а также соотвествующую ViewModel
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
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        getCountryList()
        progressHandler()
        return view
    }

    /**
     * Метод осуществляет наблюдение за изменением статуса загрузки у
     * ViewModel и в зависимости от значения устанавливает видимость View
     * */
    private fun progressHandler() {
        mainViewModel.progress().observe(viewLifecycleOwner, {
            when (it) {
                true -> progress_text_view.visibility = View.INVISIBLE
                false -> progress_text_view.visibility = View.VISIBLE
            }
        })
    }

    /**
     * Метод получает список стран у ViewModel и передаёт его RecyclerView,
     * меняет статус прогресса выполнения загрузки, устанавливает разделители
     * с помощью 'DividerItemDecoration', передаёт список стран и состояние
     * прогресса во 'MainViewModel', обрабатывая исключения
     * */
    @SuppressLint("CheckResult")
    fun getCountryList() {
        mainViewModel.setProgress(false)
        listViewModel.getCountryList()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isSuccessful) {
                    recycler_view.layoutManager = LinearLayoutManager(this.context)
                    recycler_view.adapter = ListViewAdapter(it.body()!!, this)
                    recycler_view.addItemDecoration(
                        DividerItemDecoration(
                            requireContext(),
                            DividerItemDecoration.VERTICAL
                        )
                    )
                    mainViewModel.setCountryList(it.body()!!)
                    mainViewModel.setProgress(true)
                } else {
                    mainViewModel.setException(it.errorBody().toString())
                }
            }, {
                it.message?.let { ex -> mainViewModel.setException(ex) }
            })
    }

    /**
     * Метод запускает действия после клика по значению списка пользователем:
     * 1) получение страны по номеру позиции списка;
     * 2) переход с 1-ого экрана на 2-ой экран
     *
     * @param position номер позиции в списке стран RecyclerView
     * */
    override fun onMessageClick(position: Int) {
        mainViewModel.setSelectedCountry(position)
        mainActivity.navController.navigate(R.id.action_listFragment_to_countryFragment)
    }

}