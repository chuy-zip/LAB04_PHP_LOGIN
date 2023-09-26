package com.example.signin_php

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SignInActivity : AppCompatActivity() {

    private lateinit var editTextEmailUser: EditText
    private lateinit var editTextPass: EditText
    private lateinit var buttonSignIn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        editTextEmailUser = findViewById(R.id.editTextEmailUser)
        editTextPass = findViewById(R.id.editTextPass)
        buttonSignIn = findViewById(R.id.buttonSignIn)

        buttonSignIn.setOnClickListener {
            val intent = Intent(this, SuccesLogin::class.java)

            val username = editTextEmailUser.text.toString()
            val password = editTextPass.text.toString()

            intent.putExtra("username", username)
            intent.putExtra("password", password)

            startActivity(intent)
        }
    }

}