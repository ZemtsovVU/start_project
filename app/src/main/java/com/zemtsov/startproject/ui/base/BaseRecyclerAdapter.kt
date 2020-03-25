package com.zemtsov.startproject.ui.base

import androidx.recyclerview.widget.RecyclerView

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
abstract class BaseRecyclerAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    private val items = mutableListOf<T>()

    @Synchronized
    fun getItem(position: Int): T? {
        return try {
            items[position]
        } catch (e: IndexOutOfBoundsException) {
            null
        }
    }

    @Synchronized
    fun getItems(): List<T> {
        return items
    }

    @Synchronized
    fun setItem(position: Int, item: T?) {
        item?.let {
            if (items.size > position) {
                items[position] = item
                notifyItemChanged(position)
            } else {
                addItem(item)
            }
        }
    }

    @Synchronized
    fun setItems(items: List<T>?) {
        items?.let {
            this.items.clear()
            this.items.addAll(items)
            notifyDataSetChanged()
        }
    }

    @Synchronized
    fun addItem(item: T?) {
        item?.let {
            items.add(item)
            val positionInsert = items.size - 1
            notifyItemInserted(positionInsert)
        }
    }

    @Synchronized
    fun addItems(items: List<T>?) {
        items?.let {
            val positionStart = this.items.size
            val itemCount = items.size
            this.items.addAll(items)
            notifyItemRangeInserted(positionStart, itemCount)
        }
    }

    @Synchronized
    fun removeItem(item: T?) {
        item?.let {
            val removeIndex = items.indexOf(item)
            if (removeIndex == INDEX_INVALID) {
                return
            }
            items.removeAt(removeIndex)
            notifyItemRemoved(removeIndex)
        }
    }

    @Synchronized
    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    companion object {
        private val INDEX_INVALID = -1
    }
}