package com.deanu.ktorandroidchat.util

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder(container: ViewGroup) : RecyclerView.ViewHolder(container)

class BaseItemCallback<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.toString() == newItem.toString()

    override fun areContentsTheSame(oldItem: T, newItem: T) =
        oldItem.equalsTo(newItem)
}

fun <T> Any.equalsTo(obj: T): Boolean {
    return this == obj
}