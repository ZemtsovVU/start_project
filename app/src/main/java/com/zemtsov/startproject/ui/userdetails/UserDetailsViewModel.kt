package com.zemtsov.startproject.ui.userdetails

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
class UserDetailsViewModel : ViewModel() {

    var navController: NavController? = null

    override fun onCleared() {
        super.onCleared()
        navController = null
    }
}