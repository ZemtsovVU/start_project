package com.zemtsov.startproject.ui.userdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.zemtsov.startproject.data.entity.User

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
class UserDetailsViewModel : ViewModel() {

    var navController: NavController? = null

    private var _userLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User> get() = _userLiveData

    fun setUser(user: User) {
        _userLiveData.value = user
    }

    override fun onCleared() {
        super.onCleared()
        navController = null
    }
}