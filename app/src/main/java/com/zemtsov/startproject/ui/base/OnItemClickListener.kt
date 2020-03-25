package com.zemtsov.startproject.ui.base

import android.view.View

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
interface OnItemClickListener<T> {
    fun onItemClick(itemView: View, position: Int, item: T?)
}