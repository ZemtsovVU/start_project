package com.zemtsov.startproject.util

import android.view.ViewGroup

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
object ViewUtil {
    fun toggleEnableState(rootViewGroup: ViewGroup, enabled: Boolean) =
        toggleEnableStateRecursively(rootViewGroup, enabled)
}

fun ViewGroup.toggleEnableState(enabled: Boolean) = toggleEnableStateRecursively(this, enabled)

private fun toggleEnableStateRecursively(rootViewGroup: ViewGroup, enabled: Boolean) {
    for (i in 0 until rootViewGroup.childCount) {
        val child = rootViewGroup.getChildAt(i)
        child.isEnabled = enabled
        if (child is ViewGroup) {
            toggleEnableStateRecursively(child, enabled)
        }
    }
}