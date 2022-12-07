package com.joduma.equipodinamita.models

import com.google.gson.annotations.SerializedName


class UserPass (
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") var name: String,
    @SerializedName("slug") var slug: String,
    @SerializedName("address") var address: String? = null,
    @SerializedName("email") val email: String,
    @SerializedName("role") val role: String? = null,
    ){
    override fun toString(): String {
        return "UserPass(id=$id, name='$name', slug='$slug', address=$address, email='$email', role=$role)"
    }
}