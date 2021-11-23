package crabster.rudakov.sberschoollesson19hwk.ui.country.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import crabster.rudakov.sberschoollesson19hwk.R
import crabster.rudakov.sberschoollesson19hwk.data.model.Hits

class ImageViewAdapter(
    private val values: List<Hits>,
) : RecyclerView.Adapter<ImageViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView? = null

        init {
            imageView = itemView.findViewById(R.id.imageView)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(values[position].webformatURL).resize(500, 500).into(holder.imageView);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return values.size
    }



}