package com.zemtsov.startproject.ui.userlist

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
class UserListViewModel : ViewModel() {

    var navController: NavController? = null

    val onDetailsButtonClickListener = View.OnClickListener {
        val action = UserListFragmentDirections.actionUserListFragmentToUserDetailsFragment()
        navController!!.navigate(action)
    }

    override fun onCleared() {
        super.onCleared()
        navController = null
    }
}