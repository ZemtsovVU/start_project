package com.zemtsov.startproject.data.source.remote

import com.zemtsov.startproject.data.entity.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
interface UsersRestClient {

    @GET("users/{login}")
    fun user(@Path("login") login: String): Single<User>

    @GET("users")
    fun users(): Single<List<User>>
}