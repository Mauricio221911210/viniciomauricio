package com.joduma.equipodinamita.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class ResponseHttp (
    @SerializedName("user") val user: JsonObject,
    @SerializedName("data") val data: JsonObject,
    @SerializedName("token") val token: String,
    @SerializedName("message") val message: String
) {
    override fun toString(): String {
        return "ResponseHttp(user=$user, data=$data, token='$token', message='$message')"
    }
}