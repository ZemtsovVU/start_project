package com.zemtsov.startproject.ui.base

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
class Resource<T>(val status: Status, val data: T? = null, val error: Throwable? = null) {

    companion object {
        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING)
        }

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(error: Throwable?): Resource<T> {
            return Resource(Status.ERROR, null, error)
        }
    }

    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }
}