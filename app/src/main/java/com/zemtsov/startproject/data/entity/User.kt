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
data class User(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("login")
    val login: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("html_url")
    val htmlUrl: String? = null,
    @SerializedName("repos_url")
    val reposUrl: String? = null
) : Parcelable