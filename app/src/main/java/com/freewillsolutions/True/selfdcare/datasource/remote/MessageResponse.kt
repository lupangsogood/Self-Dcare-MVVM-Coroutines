package com.freewillsolutions.True.selfdcare.datasource.remote

import com.google.gson.annotations.SerializedName

data class MessageResponse(@SerializedName("message") var message: String = "")