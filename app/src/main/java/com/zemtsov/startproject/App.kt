package com.zemtsov.startproject

import android.app.Application
import com.zemtsov.startproject.di.AppComponentHolder
import com.zemtsov.startproject.di.AppModule
import com.zemtsov.startproject.di.DaggerAppComponent

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDi()
    }

    private fun initDi() {
        // There is no need to unbind this component, because the system kills the process,
        // accordingly, all objects created by this process are destroyed.
        AppComponentHolder.bindComponent(
            DaggerAppComponent.builder()
                .appModule(AppModule(applicationContext))
                .build()
        )
    }
}