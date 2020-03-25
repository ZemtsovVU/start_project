package com.zemtsov.startproject.ui.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.zemtsov.startproject.data.entity.User
import com.zemtsov.startproject.di.AppComponentHolder
import com.zemtsov.startproject.domain.UsersUseCase
import com.zemtsov.startproject.ui.base.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
class UserListViewModel : ViewModel() {

    var navController: NavController? = null

    @Inject
    lateinit var usersUseCase: UsersUseCase
    private var usersDisposable: Disposable? = null
    private val _usersLiveData = MutableLiveData<Resource<List<User>>>()
    val usersLiveData: LiveData<Resource<List<User>>> get() = _usersLiveData

    init {
        AppComponentHolder.component().inject(this)

        usersDisposable = usersUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _usersLiveData.value = Resource.loading() }
            .subscribe(
                { _usersLiveData.value = Resource.success(it) },
                { _usersLiveData.value = Resource.error(it) }
            )
    }

    // TODO: Need to pass user_id if use database of another cache variants
    fun onUserClick(user: User) {
        val action = UserListFragmentDirections.actionUserListFragmentToUserDetailsFragment(user)
        navController!!.navigate(action) // !! fail-fast behavior (https://en.wikipedia.org/wiki/Fail-fast)
    }

    override fun onCleared() {
        super.onCleared()
        navController = null
        usersDisposable?.dispose()
    }
}