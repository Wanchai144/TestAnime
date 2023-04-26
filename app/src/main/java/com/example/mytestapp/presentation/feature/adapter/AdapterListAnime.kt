package com.example.mytestapp.presentation.feature.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mytestapp.R
import com.example.mytestapp.domain.model.DataCustomAnime
import kotlinx.android.synthetic.main.item_icon.view.*

class AdapterListAnime :
    RecyclerView.Adapter<AdapterListAnime.FeatureViewHolder>() {

    var onSelectItem: ((DataCustomAnime) -> Unit)? = null

    var data: List<DataCustomAnime> = arrayListOf()
        set(value) {
            val result = DiffUtil.calculateDiff(CategoryDiffCallback(field, value))
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeatureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_icon, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val item = data[holder.bindingAdapterPosition]
            holder.bind(item)
        }
    }

    open class FeatureViewHolder(view: View) : RecyclerView.ViewHolder(view)

    inner class ViewHolder(view: View) : FeatureViewHolder(view) {
        fun bind(data: DataCustomAnime) = with(itemView) {
            Glide.with(context)
                .load(data.image)
                .fitCenter()
                .into(imageView)
            tvDescription.text = data.title
            tvDetail.text = data.detail
            itemView.setOnClickListener { onSelectItem?.invoke(data) }
        }
    }

    inner class CategoryDiffCallback(private val o: List<Any>, private val n: List<Any>) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int = o.size

        override fun getNewListSize(): Int = n.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldObject = o[oldItemPosition]
            val newObject = n[newItemPosition]
            return if (oldObject is DataCustomAnime && newObject is DataCustomAnime) {
                oldObject.title == newObject.title && oldObject.detail == newObject.detail
            } else {
                o == n
            }
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            o[oldItemPosition] == n[newItemPosition]
    }



}

