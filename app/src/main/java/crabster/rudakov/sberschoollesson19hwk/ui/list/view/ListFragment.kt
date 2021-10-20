
package crabster.rudakov.sberschoollesson19hwk.ui.list.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import crabster.rudakov.sberschoollesson19hwk.R
import crabster.rudakov.sberschoollesson19hwk.ui.list.adapter.IListItemListener
import crabster.rudakov.sberschoollesson19hwk.ui.list.adapter.ListViewAdapter
import crabster.rudakov.sberschoollesson19hwk.ui.list.viewModel.ListViewModel
import crabster.rudakov.sberschoollesson19hwk.ui.list.viewModel.ListViewModelFactory
import crabster.rudakov.sberschoollesson19hwk.ui.main.view.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(), IListItemListener {

    private lateinit var mainActivity: MainActivity
    private lateinit var listViewModel: ListViewModel

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
        mainActivity = context as MainActivity
        val factory = ListViewModelFactory(activity?.application)
        listViewModel = ViewModelProvider(this, factory).get(ListViewModel::class.java)
        getCountryList()
        progressHandler()
        return view
    }

    /**
     * Метод осуществляет наблюдение за изменением статуса загрузки у
     * ViewModel и в зависимости от значения устанавливает видимость View
     * */
    private fun progressHandler() {
        mainActivity.mainViewModel.progress().observe(viewLifecycleOwner, {
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
        mainActivity.mainViewModel.setProgress(false)
        listViewModel.getCountryList()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isSuccessful) {
                    recycler_view.layoutManager = LinearLayoutManager(this.context)
                    recycler_view.adapter = ListViewAdapter(it.body()!!, this)
                    recycler_view.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
                    mainActivity.mainViewModel.setCountryList(it.body()!!)
                    mainActivity.mainViewModel.setProgress(true)
                } else {
                    mainActivity.mainViewModel.setException(it.errorBody().toString())
                }
            }, {
                it.message?.let { ex -> mainActivity.mainViewModel.setException(ex) }
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
        mainActivity.mainViewModel.setSelectedCountry(position)
        mainActivity.navController.navigate(R.id.action_listFragment_to_countryFragment)
    }

}