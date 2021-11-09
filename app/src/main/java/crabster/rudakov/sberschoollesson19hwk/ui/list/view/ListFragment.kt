package crabster.rudakov.sberschoollesson19hwk.ui.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import crabster.rudakov.sberschoollesson19hwk.R
import crabster.rudakov.sberschoollesson19hwk.ui.main.factory.ViewModelFactory
import crabster.rudakov.sberschoollesson19hwk.ui.list.adapter.IListItemListener
import crabster.rudakov.sberschoollesson19hwk.ui.list.adapter.ListViewAdapter
import crabster.rudakov.sberschoollesson19hwk.ui.list.viewModel.ListViewModel
import crabster.rudakov.sberschoollesson19hwk.ui.main.view.MainActivity
import crabster.rudakov.sberschoollesson19hwk.ui.main.viewModel.MainViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.launch
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
        lifecycleScope.launch {
            listViewModel.getCountryList()
        }
        progressHandler()
        setObservers()
        return view
    }

    private fun setObservers() {
        listViewModel.countryList().observe(viewLifecycleOwner, {
            recycler_view.layoutManager = LinearLayoutManager(this.context)
            recycler_view.adapter = ListViewAdapter(it, this)
            mainViewModel.setCountryList(it)
            mainViewModel.setProgress(true)
        })
        listViewModel.exception().observe(
            viewLifecycleOwner,
            {
                mainViewModel.setException(it)
            }
        )
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