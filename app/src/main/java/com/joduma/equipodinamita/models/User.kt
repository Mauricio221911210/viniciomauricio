package com.joduma.equipodinamita.models

import com.google.gson.annotations.SerializedName

class User (
    @SerializedName("id") val id: Long? = null,
    @SerializedName("name") var name: String,
    @SerializedName("slug") var slug: String? = "",
    @SerializedName("address") var address: String? = null,
    @SerializedName("email") val email: String,
    @SerializedName("password") var password: String? = "",
    @SerializedName("role") val role: String? = null,
    @SerializedName("remember_token") val remember_token: String? = null,

    ) {
    override fun toString(): String {
        return "User(id=$id, name='$name', slug='$slug', address=$address, email='$email', password=$password, role=$role, remember_token=$remember_token)"
    }
}