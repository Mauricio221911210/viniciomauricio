package com.joduma.equipodinamita.routes

import com.joduma.equipodinamita.models.ResponseHttp
import com.joduma.equipodinamita.models.User
import com.joduma.equipodinamita.models.UserPass
import retrofit2.Call
import retrofit2.http.*

interface UsersRoutes {

    @POST( "users" )
    fun  register(@Body user: User): Call<ResponseHttp>

    @PUT("users/{slug}")
    fun  update(@Body user: Any ,@Path("slug") slug: String): Call<ResponseHttp>


    @FormUrlEncoded
    @POST( "login" )
    fun login(@Field("email") email: String, @Field("password") password: String):
            Call<ResponseHttp>
}











