package com.joduma.equipodinamita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.gson.Gson
import com.joduma.equipodinamita.activities.RegisterActivity
import com.joduma.equipodinamita.activities.client.home.ClientHomeActivity
import com.joduma.equipodinamita.models.ResponseHttp
import com.joduma.equipodinamita.models.User
import com.joduma.equipodinamita.providers.UsersProvider
import com.joduma.equipodinamita.utils.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var TAG = "MainActivity"
    private var prefs: SharedPref? = null

    var imageViewGoToRegister: ImageView? = null
    var editTextEmail: EditText? = null
    var editTextPassword: EditText? = null
    var buttonLogin: Button? = null
    var usersProvider = UsersProvider()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getUserFromSession()

        imageViewGoToRegister = findViewById(R.id.imageview_got_to_register)
        editTextEmail = findViewById(R.id.edittext_email)
        editTextPassword = findViewById(R.id.edittext_password)
        buttonLogin = findViewById(R.id.btn_login)
        imageViewGoToRegister?.setOnClickListener { goToRegister() }
        buttonLogin?.setOnClickListener { login() }

    }

    private fun login() {
        val email = editTextEmail?.text.toString() // NULL POINTER EXCEPTION
        val password = editTextPassword?.text.toString()


        if (isValidForm(email, password)) {

            usersProvider.login(email, password)?.enqueue(object : Callback<ResponseHttp> {
                override fun onResponse(
                    call: Call<ResponseHttp>, response: Response<ResponseHttp>
                ) {
                    Log.d("MainActivity", "Response : ${response.body()}")

                    if (response.body()?.message != null) {
                        Toast.makeText(
                            this@MainActivity,
                            "Datos de Acceso Incorrectos",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Logeado Correctamente",
                            Toast.LENGTH_LONG
                        ).show()

                        saveUserInSession(response.body()?.user.toString())
                        goToClientHome()
                    }
                }

                override fun onFailure(p0: Call<ResponseHttp>, t: Throwable) {
                    Log.d("MainActivity", "Hubo un error")
                    Toast.makeText(
                        this@MainActivity,
                        "Datos de Acceso Incorrectos",
                        Toast.LENGTH_LONG
                    ).show()
                }

            })

            /*Toast.makeText(this, "El formulario es valido", Toast.LENGTH_LONG).show()*/
        } else {
            Toast.makeText(this, "No es valido", Toast.LENGTH_LONG).show()
        }

        /* Log.d("MainActivity", "El username es: $username")
         Log.d("MainActivity", "El password es: $password")*/
    }

    private fun getUserFromSession(){
        val sharedPref = SharedPref(this)
        val gson = Gson()

        if(!sharedPref.getData("user").isNullOrBlank()){
            val user = gson.fromJson(sharedPref.getData("user"), User::class.java)
            goToClientHome()
        }
    }

    private fun goToClientHome(){
        var i = Intent(this, ClientHomeActivity::class.java)
        startActivity(i)
    }

    private fun saveUserInSession(data: String) {
        val sharedPref = SharedPref(this)
        val gson = Gson()
        val user = gson.fromJson(data, User::class.java)
        sharedPref.save("user", user)
    }


    // fun String.isUsernameValid(): Boolean {
    //   return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    //}

    private fun isValidForm(email: String, password: String): Boolean {

        if (email.isBlank()) {
            return false
        }

        if (password.isBlank()) {
            return false
        }

        /* if (!username.isUsernameValid()) {
             return false
         }*/

        return true
    }

    private fun goToRegister() {
        val l = Intent(this, RegisterActivity::class.java)
        startActivity(l)
    }



}
