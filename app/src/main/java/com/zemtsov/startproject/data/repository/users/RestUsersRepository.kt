package com.zemtsov.startproject.data.repository.users

import com.zemtsov.startproject.data.entity.User
import com.zemtsov.startproject.data.source.remote.UsersRestClient
import io.reactivex.Single

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
class RestUsersRepository(private val usersRestClient: UsersRestClient) : UsersRepository {

    override fun user(login: String): Single<User> {
        // TODO: Handle cache (realm, room, paperdb, etc.)
        return usersRestClient.user(login)
    }

    override fun users(): Single<List<User>> {
        // TODO: Handle cache (realm, room, paperdb, etc.)
        return usersRestClient.users()
    }
}