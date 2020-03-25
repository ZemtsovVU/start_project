package com.zemtsov.startproject.data.repository.session

import android.content.Context
import android.content.SharedPreferences
import com.zemtsov.startproject.data.entity.SessionInfo
import javax.inject.Inject

/**
 * Just for example.
 *
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */

private const val TAG = "PREFS_SESSION_REPOSITORY_"
private const val KEY_ACCESS_TOKEN = TAG + "KEY_ACCESS_TOKEN"
private const val KEY_ACCESS_TOKEN_EXPIRE = TAG + "KEY_ACCESS_TOKEN_EXPIRE"
private const val KEY_REFRESH_TOKEN = TAG + "KEY_REFRESH_TOKEN"

class PrefsSessionRepository @Inject constructor(context: Context) : SessionRepository {

    private val prefs: SharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE)

    private var _sessionInfo: SessionInfo? = null

    override fun sessionInfo(): SessionInfo {
        if (_sessionInfo == null) {
            _sessionInfo = SessionInfo(
                prefs.getString(KEY_ACCESS_TOKEN, null),
                prefs.getString(KEY_ACCESS_TOKEN_EXPIRE, null),
                prefs.getString(KEY_REFRESH_TOKEN, null)
            )
        }
        return _sessionInfo!!
    }

    override fun setSessionInfo(sessionInfo: SessionInfo) {
        _sessionInfo = sessionInfo
        with(prefs.edit()) {
            putString(KEY_ACCESS_TOKEN, sessionInfo.accessToken)
            putString(KEY_ACCESS_TOKEN_EXPIRE, sessionInfo.accessTokenExpire)
            putString(KEY_REFRESH_TOKEN, sessionInfo.refreshToken)
            apply()
        }
    }

    override fun isAuthorized(): Boolean {
        return !sessionInfo().accessToken.isNullOrBlank()
    }
}