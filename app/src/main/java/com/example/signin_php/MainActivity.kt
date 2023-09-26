package com.example.signin_php

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var editTextEmailUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var registerText: TextView
    private lateinit var errorTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextEmailUsername = findViewById(R.id.editTextEmailUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        registerText = findViewById(R.id.registerText)
        errorTextView = findViewById(R.id.textViewErrorLogin)

        registerText.paintFlags = registerText.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        errorTextView.visibility = View.GONE

        buttonLogin.setOnClickListener {
            val username = editTextEmailUsername.text.toString()
            val password = editTextPassword.text.toString()

            GlobalScope.launch(Dispatchers.Main){
                val jwt = loginAndStoreJwt(applicationContext, username, password)

                if(jwt != null){

                    errorTextView.visibility = View.GONE
                    val intent = Intent(applicationContext, SuccesLogin::class.java)

                    intent.putExtra("username", username)
                    intent.putExtra("password", password)

                    startActivity(intent)
                }
                else {
                    errorTextView.visibility = View.VISIBLE
                }
            }
        }

        registerText.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    suspend fun loginAndStoreJwt(context: Context, username: String, password: String): String? {
        val json = JSONObject()
        json.put("username", username)
        json.put("password", password)

        val mediaType = "application/x-www-form-urlencoded; charset=UTF-8".toMediaType()
        val requestBody = json.toString().toRequestBody(mediaType)

        println(username)
        println(password)
        println(json.toString())

        val request = Request.Builder()
            .url("http://192.168.43.105/phpFolderPPM/php/public/authenticate.php") // Reemplaza con la URL de tu servidor
            .post(requestBody)
            .build()

        return withContext(Dispatchers.IO) {
            val response = OkHttpClient().newCall(request).execute()
            val responseBody = response.body?.string()

            if (response.isSuccessful && responseBody != null && !responseBody.equals("error_bad_credentials")) {
                //val jwt = JSONObject(responseBody).getString("jwt")
                val jwt = responseBody
                println(jwt)
                return@withContext jwt
            } else {
                // Manejar error de autenticación u otra situación
                return@withContext null
            }
        }
    }
}
fun loginIsValid(username: String, password: String): Boolean {
    if(username == "chuy" && password  == "1234"){
        return true
    }

    return false
}
