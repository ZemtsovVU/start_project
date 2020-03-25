package com.zemtsov.startproject.data.source.remote.endpoint

import com.zemtsov.startproject.util.consts.RestUrls

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
class SimpleServerEndpoint(
    host: String = RestUrls.HOST,
    path: String? = RestUrls.PATH,
    api: String? = RestUrls.API
) : BaseServerEndpoint(host, path, api)