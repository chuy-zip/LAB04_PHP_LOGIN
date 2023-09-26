package com.example.signin_php

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var editTextEmailUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var registerText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextEmailUsername = findViewById(R.id.editTextEmailUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        registerText = findViewById(R.id.registerText)

        registerText.paintFlags = registerText.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        buttonLogin.setOnClickListener {
            val intent = Intent(this, SuccesLogin::class.java)
            val username = editTextEmailUsername.text.toString()
            val password = editTextPassword.text.toString()

            intent.putExtra("username", username)
            intent.putExtra("password", password)

            startActivity(intent)
        }

        registerText.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

    }
}