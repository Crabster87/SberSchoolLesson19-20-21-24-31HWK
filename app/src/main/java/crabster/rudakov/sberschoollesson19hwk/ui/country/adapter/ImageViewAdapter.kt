package crabster.rudakov.sberschoollesson19hwk.ui.country.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import crabster.rudakov.sberschoollesson19hwk.R
import crabster.rudakov.sberschoollesson19hwk.data.model.Hits

/**
 * Класс-адаптер списка изображений RecyclerView(2-ой экран)
 * */
class ImageViewAdapter(private val values: List<Hits>) :
    RecyclerView.Adapter<ImageViewAdapter.ViewHolder>() {

    /**
     * Класс-держатель элемента View списка изображений
     * */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView? = null

        init {
            imageView = itemView.findViewById(R.id.imageView)
        }
    }

    /**
     * Метод загружает изображения по URL из списка с помощью
     * сторонней библиотеки 'Picasso' и устанавливает их в
     * соответствующие View, принадлежащие элементу списка
     *
     * @param holder объект ViewHolder()
     * @param position номер позиции в списке изображений
     * */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get()
            .load(values[position].webformatURL)
            .resize(500, 400)
            .into(holder.imageView)
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
            .inflate(R.layout.image_item, parent, false)
        return ViewHolder(itemView)
    }

    /**
     * Метод возвращает количество элементов списка изображений
     *
     * @return размер списка List<Hits>
     * */
    override fun getItemCount(): Int {
        return values.size
    }

}