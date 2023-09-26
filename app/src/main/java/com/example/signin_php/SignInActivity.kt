package com.example.signin_php

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class SignInActivity : AppCompatActivity() {

    private lateinit var editTextEmailUser: EditText
    private lateinit var editTextPass: EditText
    private lateinit var editTextPassConfirm: EditText
    private lateinit var buttonSignIn: Button
    private lateinit var signInErrorTextView: TextView
    private lateinit var editTextUserName:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        editTextPassConfirm = findViewById(R.id.editTextPassConfirm)
        editTextUserName = findViewById(R.id.editTextNewName)
        signInErrorTextView = findViewById(R.id.textViewErrorSignIn)
        editTextEmailUser = findViewById(R.id.editTextEmailUser)
        editTextPass = findViewById(R.id.editTextPass)
        buttonSignIn = findViewById(R.id.buttonSignIn)

        buttonSignIn.setOnClickListener {

            val username = editTextEmailUser.text.toString()
            val password = editTextPass.text.toString()
            val confirmPassword = editTextPassConfirm.text.toString()
            val name = editTextUserName.text.toString()

            if(password == confirmPassword){
                GlobalScope.launch(Dispatchers.Main){
                    val jwt = registerAndStoreJwt(applicationContext, username, password, name)

                    if(jwt != null){

                        signInErrorTextView.visibility = View.GONE
                        val intent = Intent(applicationContext, SuccesLogin::class.java)

                        intent.putExtra("username", username)
                        intent.putExtra("jwt", jwt)

                        startActivity(intent)
                    }
                    else {
                        signInErrorTextView.visibility = View.VISIBLE
                    }
                }
            }
            else{
                Toast.makeText(this, "Las contraseñas ingresadas no son iguales", Toast.LENGTH_LONG).show()
            }
        }
    }

    suspend fun registerAndStoreJwt(context: Context, username: String, password: String, name: String): String? {
        val json = JSONObject()
        json.put("username", username)
        json.put("password", password)
        json.put("name", name)

        val mediaType = "application/x-www-form-urlencoded; charset=UTF-8".toMediaType()
        val requestBody = json.toString().toRequestBody(mediaType)

        println(username)
        println(password)
        println(json.toString())

        val request = Request.Builder()
            .url("http://192.168.1.73/phpFolderPPM/php/public/register.php") // Reemplaza con la URL de tu servidor
            .post(requestBody)
            .build()

        return withContext(Dispatchers.IO) {
            val response = OkHttpClient().newCall(request).execute()
            val responseBody = response.body?.string()

            if (response.isSuccessful && responseBody != null && !responseBody.equals("error_username_exists")) {
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

fun signInIsValid(username: String): Boolean {
    if(username != "andrew"){
        return true
    }
    return false
}