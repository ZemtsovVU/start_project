package com.zemtsov.startproject.di.holder

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
abstract class BaseComponentHolder<T> : ComponentHolder<T> {

    private var component: T? = null

    override fun component(): T {
        return component
            ?: throw ComponentNotBindException(
                "Component $component not bind yet. You must call bindComponent() first."
            )
    }

    override fun bindComponent(component: T) {
        this.component = component
    }

    override fun unbindComponent() {
        this.component = null
    }
}

class ComponentNotBindException(msg: String) : NullPointerException(msg)