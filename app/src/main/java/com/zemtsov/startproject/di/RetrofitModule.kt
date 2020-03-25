package com.zemtsov.startproject.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.zemtsov.startproject.data.repository.SessionRepository
import com.zemtsov.startproject.data.source.remote.UsersRestClient
import com.zemtsov.startproject.data.source.remote.endpoint.ServerEndpoint
import com.zemtsov.startproject.data.source.remote.endpoint.SimpleServerEndpoint
import com.zemtsov.startproject.util.consts.DateConst
import com.zemtsov.startproject.util.consts.RestOptions
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun provideUsersRestClient(retrofit: Retrofit): UsersRestClient {
        return retrofit.create(UsersRestClient::class.java)
    }

//  @Singleton
//  @Provides
//  fun provideNotificationRestClient(retrofit: Retrofit): NotificationRestClient {
//    return retrofit.create(NotificationRestClient::class.java)
//  }

    @Singleton
    @Provides
    fun provideRetrofit(endpoint: ServerEndpoint, gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endpoint.url())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideServerEndpoint(): ServerEndpoint {
        return SimpleServerEndpoint()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setDateFormat(DateConst.PATTERN_FULL_DATE)
            .create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .retryOnConnectionFailure(true)
            .connectTimeout(RestOptions.TIMEOUT_CONNECTION_SECONDS, TimeUnit.SECONDS)
            .readTimeout(RestOptions.TIMEOUT_READ_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(RestOptions.TIMEOUT_WRITE_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(sessionRepository: SessionRepository): Interceptor {
        return Interceptor { chain ->
            if (sessionRepository.isAuthorized()) {
                val originalRequest = chain.request()
                var modifiedRequest = originalRequest

                val header = originalRequest.header(RestOptions.HEADER_KEY_MARKER)
                @Suppress("ControlFlowWithEmptyBody")
                if (header == RestOptions.HEADER_VALUE_MARKER_NON_AUTH) {
                    /*Do nothing*/
                } else {
                    val headerValue =
                        RestOptions.HEADER_VALUE_BEARER + sessionRepository.sessionInfo().accessToken
                    modifiedRequest = originalRequest.newBuilder()
                        .header(RestOptions.HEADER_KEY_AUTH, headerValue)
                        .build()
                }

                chain.proceed(modifiedRequest)
            } else {
                val originalRequest = chain.request()
                chain.proceed(originalRequest)
            }
        }
    }
}