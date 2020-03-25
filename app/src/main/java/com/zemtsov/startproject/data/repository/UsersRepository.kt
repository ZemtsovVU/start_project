package com.zemtsov.startproject.data.repository

import com.zemtsov.startproject.data.entity.User
import io.reactivex.Single

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
interface UsersRepository {

    fun user(login: String): Single<User>

    fun users(): Single<List<User>>
}