package com.zemtsov.startproject.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
object KeyboardUtil {
    fun showKeyboard(view: View) = showSoftInput(view)

    fun hideKeyboard(anchorView: View) = hideSoftInputFromWindow(anchorView)
}

fun View.showKeyboard() = showSoftInput(this)

fun View.hideKeyboard() = hideSoftInputFromWindow(this)

private fun showSoftInput(view: View) {
    val context = view.context.applicationContext
    if (view.requestFocus()) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}

private fun hideSoftInputFromWindow(anchorView: View) {
    val context = anchorView.context.applicationContext
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val windowToken = anchorView.windowToken
    imm.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}