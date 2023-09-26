package com.example.signin_php

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SuccesLogin : AppCompatActivity() {

    private lateinit var editTextSuccess: TextView
    private lateinit var buttonSuccess: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_succes_login)

        editTextSuccess = findViewById(R.id.textViewSuccessCard)
        buttonSuccess = findViewById(R.id.buttonSucces)

        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")

        buttonSuccess.setOnClickListener {
            editTextSuccess.setText("$username y $password")
        }
    }
}