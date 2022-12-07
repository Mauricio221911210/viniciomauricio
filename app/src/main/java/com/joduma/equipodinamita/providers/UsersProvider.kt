package com.joduma.equipodinamita.providers

import com.joduma.equipodinamita.api.ApiRoutes
import com.joduma.equipodinamita.models.ResponseHttp
import com.joduma.equipodinamita.models.User
import com.joduma.equipodinamita.routes.UsersRoutes
import retrofit2.Call

class UsersProvider {
    private var usersRoutes: UsersRoutes? = null

    init {
        val  api = ApiRoutes()
        usersRoutes = api.getUsersRoutes()
    }

    fun register(user: User): Call<ResponseHttp>? {
        return usersRoutes?.register(user)
    }

    fun update( user: Any, slug: String ): Call<ResponseHttp>?{
        return usersRoutes?.update(user, slug)
    }



    fun login(email: String, password: String): Call<ResponseHttp>? {
        return usersRoutes?.login(email, password)
    }
}














