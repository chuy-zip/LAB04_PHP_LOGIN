package com.example.signin_php

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

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

        buttonLogin.setOnClickListener {
            val username = editTextEmailUsername.text.toString()
            val password = editTextPassword.text.toString()

            if(loginIsValid(username,password)){
                val intent = Intent(this, SuccesLogin::class.java)

                intent.putExtra("username", username)
                intent.putExtra("password", password)

                startActivity(intent)
            }
            else {
                errorTextView.visibility = View.VISIBLE
            }

        }

        registerText.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

    }
}
fun loginIsValid(username: String, password: String): Boolean {
    if(username == "chuy" && password  == "1234"){
        return true
    }

    return false
}
