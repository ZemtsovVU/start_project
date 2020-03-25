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
class UsersUseCase @Inject constructor(private val usersRepository: UsersRepository) {

    // TODO: Change to Kotlin coroutines impl. Rx impl just for example
    fun execute(): Single<List<User>> {
        return usersRepository.users()
    }
}