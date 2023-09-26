package com.example.signin_php

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SignInActivity : AppCompatActivity() {

    private lateinit var editTextEmailUser: EditText
    private lateinit var editTextPass: EditText
    private lateinit var buttonSignIn: Button
    private lateinit var signInErrorTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signInErrorTextView = findViewById(R.id.textViewErrorSignIn)
        editTextEmailUser = findViewById(R.id.editTextEmailUser)
        editTextPass = findViewById(R.id.editTextPass)
        buttonSignIn = findViewById(R.id.buttonSignIn)

        buttonSignIn.setOnClickListener {

            val username = editTextEmailUser.text.toString()
            val password = editTextPass.text.toString()

            if(signInIsValid(username)){
                signInErrorTextView.visibility = View.GONE
                val intent = Intent(this, SuccesLogin::class.java)

                intent.putExtra("username", username)
                intent.putExtra("password", password)

                startActivity(intent)
            }
            else {
                signInErrorTextView.visibility = View.VISIBLE
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