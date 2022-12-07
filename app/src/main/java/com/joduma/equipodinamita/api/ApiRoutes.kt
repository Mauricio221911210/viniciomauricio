package com.joduma.equipodinamita.api

import com.joduma.equipodinamita.routes.ClientRoutes
import com.joduma.equipodinamita.routes.UsersRoutes

class ApiRoutes {
    val API_URL = "https://www.proyectos.dsgys.com/api/v1/"
    val retrofit = RetrofitClient()

    fun getUsersRoutes(): UsersRoutes {
        return retrofit.getClient(API_URL).create(UsersRoutes::class.java)
    }

    fun getClientsRoutes(): ClientRoutes {
        return retrofit.getClient(API_URL).create(ClientRoutes::class.java)
    }
}