package com.joduma.equipodinamita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import com.joduma.equipodinamita.activities.client.home.ClientHomeActivity
import com.joduma.equipodinamita.models.ResponseHttp
import com.joduma.equipodinamita.models.User
import com.joduma.equipodinamita.models.UserPass
import com.joduma.equipodinamita.providers.UsersProvider
import com.joduma.equipodinamita.utils.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity() {

    var body : Any? = null

    var editTextName : EditText? = null
    var editTextPassword : EditText? = null
    var buttonUpdate : Button? = null
    var sharedPref: SharedPref? = null
    var user : User? = null
    var usersProvider = UsersProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        sharedPref = SharedPref(this)

        editTextName = findViewById(R.id.et_name)
        editTextPassword = findViewById(R.id.et_password)
        buttonUpdate = findViewById(R.id.btn_save_changes)

        getUserFromSession()

        editTextName?.setText(user?.name)
        buttonUpdate?.setOnClickListener { updateData() }
    }

    private fun updateData(){
        user?.name = editTextName?.text.toString();
        user?.password = editTextPassword?.text.toString()

        if (isValidForm(user?.name!!)) {



            if(!editTextPassword?.text.isNullOrBlank()){

                 body = User(
                    slug = user?.slug!!,
                    name = user?.name!!,
                    email =  user?.email!!,
                    password = editTextPassword?.text.toString()

                    )
            }else {
                 body = UserPass(
                    slug = user?.slug!!,
                    name = user?.name!!,
                    email =  user?.email!!,

                )

            }

            Log.d("AdminUpdateActivity", "User : ${body}")
            usersProvider.update(body!!, user?.slug!!)?.enqueue(object : Callback<ResponseHttp> {
                override fun onResponse(
                    call: Call<ResponseHttp>, response: Response<ResponseHttp>
                ) {
                    Toast.makeText(
                        this@UpdateActivity,
                        "Datos Actualizados con Exito",
                        Toast.LENGTH_LONG
                    ).show()
                   /* Log.d( "AdminUpdate", "${response.body()}")
                    Log.d( "AdminUpdate", "${response.body()?.data}")
                    Log.d( "AdminUpdate", "${response.code()}")
                    Log.d( "AdminUpdate", "${response}")*/

                    saveUserInSession(response.body()?.data.toString())
                }

                override fun onFailure(p0: Call<ResponseHttp>, t: Throwable) {
                    Log.d("AdminUpdateActivity", "Hubo un error")
                    Toast.makeText(
                        this@UpdateActivity,
                        "Los Datos no son Correctos",
                        Toast.LENGTH_LONG
                    ).show()
                }

            })
        } else {
            Toast.makeText(this, "Nombre son Obligatorio", Toast.LENGTH_LONG).show()
        }
    }

    private fun isValidForm(name: String): Boolean {
        if (name.isBlank()) return false
        return true
    }

    private fun gotToProfile(){
        val i = Intent(this, ClientHomeActivity::class.java)
        startActivity(i)
    }

    private fun getUserFromSession(){
        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
        }
    }

    private fun saveUserInSession(data: String) {
        val sharedPref = SharedPref(this)
        val gson = Gson()
        val user = gson.fromJson(data, User::class.java)
        sharedPref.save("user", user)
    }
}
