package com.zemtsov.startproject.di

import com.zemtsov.startproject.data.repository.session.SessionRepository
import com.zemtsov.startproject.data.repository.users.UsersRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
@Singleton
@Component(modules = [AppModule::class, RetrofitModule::class, RepositoryModule::class])
interface AppComponent {

    fun sessionRepository(): SessionRepository

    fun usersRepository(): UsersRepository

//    fun inject(injectable: UserListViewModel)
}