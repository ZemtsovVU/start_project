package com.zemtsov.startproject.di

import android.content.Context
import com.zemtsov.startproject.data.repository.PrefsSessionRepository
import com.zemtsov.startproject.data.repository.RestUsersRepository
import com.zemtsov.startproject.data.repository.SessionRepository
import com.zemtsov.startproject.data.repository.UsersRepository
import com.zemtsov.startproject.data.source.remote.UsersRestClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideSessionRepository(context: Context): SessionRepository {
        return PrefsSessionRepository(context)
    }

    // TODO: Move this flow to @Subcomponent with local scope
    @Singleton
    @Provides
    fun provideUsersRepository(usersRestClient: UsersRestClient): UsersRepository {
        return RestUsersRepository(usersRestClient)
    }
}