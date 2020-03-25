package com.zemtsov.startproject.data.repository.session

import com.zemtsov.startproject.data.entity.SessionInfo

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
interface SessionRepository {

    fun sessionInfo(): SessionInfo

    fun setSessionInfo(sessionInfo: SessionInfo)

    fun isAuthorized(): Boolean
}