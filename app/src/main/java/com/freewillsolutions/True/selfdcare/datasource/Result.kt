package com.freewillsolutions.True.selfdcare.datasource

import com.google.gson.annotations.SerializedName
import java.net.HttpURLConnection


data class Result<T>(
    @SerializedName("status") var statusCode: Int = 0,
    @SerializedName("message") var message: String? = "",
    @SerializedName("body") var data: T? = null) {

    companion object {
        fun <T> success(data: T?): Result<T> = Result(HttpURLConnection.HTTP_OK, null, data)
        fun <T> error(statusCode: Int, message: String?): Result<T> = Result(statusCode, message, null)
    }

    fun isSuccessful(): Boolean = statusCode == HttpURLConnection.HTTP_OK
}