package crabster.rudakov.sberschoollesson19hwk.ui.list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import crabster.rudakov.sberschoollesson19hwk.R
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryItem
import java.util.*

/**
 * Класс-адаптер списка стран RecyclerView(1-ый экран)
 * */
class ListViewAdapter(
    private val values: List<CountryItem>,
    private val iListItemListener: IListItemListener
) : RecyclerView.Adapter<ListViewAdapter.ViewHolder>(), Filterable {

    private val list = values.toMutableList()

    /**
     * Класс-держатель элемента View списка стран
     * */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var layout: ConstraintLayout? = null
        var textView: TextView? = null
        var imageView: ImageView? = null

        init {
            layout = itemView.findViewById(R.id.layout)
            textView = itemView.findViewById(R.id.textView)
            imageView = itemView.findViewById(R.id.imageView)
        }
    }

    /**
     * Метод возвращает специальный объект, который будет
     * обеспечивать фильтрацию списка стран в RecyclerView
     * в соответствии с шаблоном. Фильтрация выполняется
     * асинхронно
     *
     * @return объект Filter()
     * */
    override fun getFilter(): Filter {
        return filter
    }

    private val filter = object : Filter() {

        /**
         * Метод выполняет фильтрацию списка стран в RecyclerView в
         * основном потоке в соответствии с заданным правилом(по
         * наличию в названии страны совпадения с введённым в поле
         * EditText буквенным символам)
         *
         * @param charSequence введённая последовательность букв
         * @return объект FilterResults(), содержащий результат
         * */
        override fun performFiltering(charSequence: CharSequence?): FilterResults {
            val filteredList = mutableListOf<CountryItem>()
            if (charSequence == null || charSequence.isEmpty()) {
                filteredList.addAll(values)
            } else {
                val filterPattern = charSequence.toString().lowercase(Locale.getDefault())
                for (item in values) {
                    if (item.name.lowercase(Locale.getDefault()).contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val result = FilterResults()
            result.values = filteredList
            return result
        }

        /**
         * Метод выполняет отрисовку полученного в результате фильтрации
         * списка стран в UI-потоке
         *
         * @param p0 введённая последовательность букв
         * @param filteredResults объект FilterResults(), содержащий результат
         * */
        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(p0: CharSequence?, filteredResults: FilterResults?) {
            list.clear()
            list.addAll(filteredResults?.values as MutableList<CountryItem>)
            notifyDataSetChanged()
        }

    }

    /**
     * Метод создаёт и возвращает ViewHolder списка
     *
     * @param parent родительская ViewGroup
     * @param viewType тип View
     * @return объект ViewHolder()
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    /**
     * Метод устанавливает значения во все View, принадлежащие
     * элементу списка, а также устанавливает слушатель клика
     *
     * @param holder объект ViewHolder()
     * @param position номер позиции в списке стран RecyclerView
     * */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView?.text = list[position].name
        holder.imageView?.setImageResource(R.drawable.ic_country)
        holder.layout?.setOnClickListener {
            iListItemListener.onMessageClick(list[position].url)
        }
    }

    /**
     * Метод возвращает количество элементов списка стран
     *
     * @return размер списка List<CountryItem>
     * */
    override fun getItemCount() = list.size

}