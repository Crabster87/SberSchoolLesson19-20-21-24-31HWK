package crabster.rudakov.sberschoollesson19hwk.ui.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import crabster.rudakov.sberschoollesson19hwk.R
import crabster.rudakov.sberschoollesson19hwk.data.model.CountryItem

/**
 * Класс-адаптер списка стран RecyclerView(1-ый экран)
 * */
class ListViewAdapter (

    private val values: List<CountryItem>,
    private val iListItemListener: IListItemListener) :
    RecyclerView.Adapter<ListViewAdapter.ViewHolder>() {

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
        holder.textView?.text = values[position].name
        holder.imageView?.setImageResource(R.drawable.ic_country)
        holder.layout?.setOnClickListener {
            iListItemListener.onMessageClick(holder.adapterPosition)
        }
    }

    /**
     * Метод возвращает количество элементов списка стран
     *
     * @return размер списка List<CountryItem>
     * */
    override fun getItemCount() = values.size

}