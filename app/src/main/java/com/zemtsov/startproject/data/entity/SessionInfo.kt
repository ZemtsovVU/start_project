package com.zemtsov.startproject.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
@Parcelize
data class SessionInfo(
    @SerializedName("accessToken")
    val accessToken: String?,
    @SerializedName("accessTokenExpire")
    val accessTokenExpire: String?,
    @SerializedName("refreshToken")
    val refreshToken: String?
) : Parcelable