package com.zemtsov.startproject.domain

import com.zemtsov.startproject.data.entity.User
import com.zemtsov.startproject.data.repository.users.UsersRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Just for example. No need to inject use cases.
 *
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
class UserUseCase @Inject constructor(private val usersRepository: UsersRepository) {

    fun execute(login: String): Single<User> {
        return usersRepository.user(login)
    }
}