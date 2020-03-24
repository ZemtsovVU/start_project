package com.zemtsov.startproject.di.holder

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
interface ComponentHolder<T> {

    fun component(): T

    fun bindComponent(component: T)

    fun unbindComponent()
}