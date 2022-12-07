package com.joduma.equipodinamita.activities.client.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.joduma.equipodinamita.MainActivity
import com.joduma.equipodinamita.R
import com.joduma.equipodinamita.UpdateActivity
import com.joduma.equipodinamita.activities.ProfileActivity
import com.joduma.equipodinamita.activities.products.ProductActivity
import com.joduma.equipodinamita.models.User
import com.joduma.equipodinamita.utils.SharedPref

class ClientHomeActivity : AppCompatActivity() {


    private val TAG = "ClientHomeActivity"
    var buttonLogout: Button? = null
    var sharedPref: SharedPref? = null
    var imageViewGoToProduct: ImageView? = null
    var imageViewGoToProfile: ImageView? = null
    var imageViewGoToClient: ImageView? = null

    var buttonUpdateProfile: Button? = null
    var textViewName: TextView? = null
    var textViewEmail: TextView? = null
    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_home)

        sharedPref = SharedPref(this)


        textViewName = findViewById(R.id.name_user)
        textViewEmail = findViewById(R.id.name_email)
        buttonUpdateProfile = findViewById(R.id.btn_update)


        imageViewGoToProfile = findViewById(R.id.button_profile)
        imageViewGoToProfile?.setOnClickListener { gotoprofile() }

        imageViewGoToProduct = findViewById(R.id.button_product)
        imageViewGoToProduct?.setOnClickListener { gotoclient() }

        imageViewGoToClient = findViewById(R.id.button_client)
        imageViewGoToClient?.setOnClickListener { gotoproduct() }

        buttonLogout = findViewById(R.id.btn_logout)
        buttonLogout?.setOnClickListener{
            logout()
        }

        getUserFromSession()


        textViewName?.setText(user?.name)
        textViewEmail?.setText(user?.email)


        buttonUpdateProfile?.setOnClickListener {
            goToUpdateProfile()
        }
    }

    private fun goToUpdateProfile (){
        val i = Intent( this, UpdateActivity::class.java )
        startActivity(i)
    }

    private fun logout(){
        sharedPref?.remove("user")
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    private fun  gotoprofile(){
        val i = Intent(this, ProfileActivity::class.java )
        startActivity(i)
    }

    private fun  gotoproduct(){
        val i = Intent(this, ProductActivity::class.java )
        startActivity(i)
    }

    private fun  gotoclient(){
        val i = Intent(this, ClientHomeActivity::class.java )
        startActivity(i)
    }

    private fun getUserFromSession(){
        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            val user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
            Log.d(TAG, "Usuario: $user")
        }
    }

}
