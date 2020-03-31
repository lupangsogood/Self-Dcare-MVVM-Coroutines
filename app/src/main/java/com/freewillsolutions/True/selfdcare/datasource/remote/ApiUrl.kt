package com.freewillsolutions.True.selfdcare.datasource.remote

class ApiUrl {
    companion object {
        private const val BASE_URL: String = ""
        private const val BASE_URL_UAT: String = ""

        fun getBaseUrl(): String = BASE_URL

        //fun getImageUrl(): String = if (BuildConfig.DEBUG) "${BASE_URL_UAT}/uploads" else "${BASE_URL}/uploads"
    }
}