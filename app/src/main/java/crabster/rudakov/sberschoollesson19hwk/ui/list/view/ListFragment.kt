package crabster.rudakov.sberschoollesson19hwk.ui.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import crabster.rudakov.sberschoollesson19hwk.R
import crabster.rudakov.sberschoollesson19hwk.ui.main.factory.ViewModelFactory
import crabster.rudakov.sberschoollesson19hwk.ui.list.adapter.IListItemListener
import crabster.rudakov.sberschoollesson19hwk.ui.list.adapter.ListViewAdapter
import crabster.rudakov.sberschoollesson19hwk.ui.list.viewModel.ListViewModel
import crabster.rudakov.sberschoollesson19hwk.ui.main.view.MainActivity
import crabster.rudakov.sberschoollesson19hwk.ui.main.viewModel.MainViewModel
import dagger.android.support.DaggerFragment
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
    private lateinit var listAdapter: ListViewAdapter

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
        listViewModel.getCountryList()

        progressHandler()
        setObservers()
        view.findViewById<EditText>(R.id.filter_edit_text).clearFocus()
        return view
    }

    /**
     * Метод получает список стран у ViewModel и передаёт его RecyclerView,
     * меняет статус прогресса выполнения загрузки, устанавливает слушатель
     * изменений в поле EditText, передаёт список стран и состояние прогресса
     * в MainViewModel, обрабатывая исключения
     * */
    private fun setObservers() {
        listViewModel.countryList().observe(viewLifecycleOwner) {
            if (recycler_view.adapter == null) {
                listAdapter = ListViewAdapter(it, this)
                recycler_view.layoutManager = LinearLayoutManager(this.context)
                recycler_view.adapter = listAdapter
                mainViewModel.setCountryList(it)
                mainViewModel.setProgress(true)
                listAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                setFilterListener()
            }

        }
        listViewModel.exception().observe(
            viewLifecycleOwner
        ) {
            mainViewModel.setException(it)
        }
    }

    /**
     * Метод осуществляет наблюдение за изменением текста в поле EditText.
     * В случае его изменения инициирует фильтрацию и отбор значений списка,
     * имеющих в названии введённую буквенную последовательность
     * */
    private fun setFilterListener() {
        filter_edit_text.addTextChangedListener {
            listAdapter.filter.filter(it)
        }
    }

    /**
     * Метод осуществляет наблюдение за изменением статуса загрузки у
     * ViewModel и в зависимости от значения устанавливает видимость View
     * */
    private fun progressHandler() {
        mainViewModel.progress().observe(viewLifecycleOwner) {
            when (it) {
                true -> progress_layout.visibility = View.INVISIBLE
                false -> progress_layout.visibility = View.VISIBLE
            }
        }
    }

    /**
     * Метод запускает действия после клика по значению списка пользователем:
     * 1) получение страны по номеру позиции списка;
     * 2) переход с 1-ого экрана на 2-ой экран
     *
     * @param url URL страны позиции списка RecyclerView
     * */
    override fun onMessageClick(url: String) {
        mainViewModel.setSelectedCountry(url)
        mainActivity.navController.navigate(R.id.action_listFragment_to_countryFragment)
    }

}