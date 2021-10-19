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

class ListViewAdapter (

    private val values: List<CountryItem>,
    private val iListItemListener: IListItemListener) :
    RecyclerView.Adapter<ListViewAdapter.ViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView?.text = values[position].name
        holder.imageView?.setImageResource(R.drawable.ic_country)
        holder.layout?.setOnClickListener {
            iListItemListener.onMessageClick(holder.adapterPosition)
        }
    }

    override fun getItemCount() = values.size

}