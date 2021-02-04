package com.example.foodie.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.foodie.R

class LoginActivity : AppCompatActivity() {

    private lateinit var etPhnNumber: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogIn: Button
    private lateinit var txtForgetPassword: TextView
    private lateinit var txtRegister: TextView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val intent = Intent(this@LoginActivity, LoggedInActivity::class.java)
        val intentRegister = Intent(this@LoginActivity, RegistrationActivity::class.java)
        val intentForgetPassword = Intent(this@LoginActivity, ForgetPasswordActivity::class.java)

        etPhnNumber = findViewById(R.id.etPhnNumber)
        etPassword = findViewById(R.id.etPassword)
        btnLogIn = findViewById(R.id.btnLogIn)
        txtForgetPassword = findViewById(R.id.txtForgetPassword)
        txtRegister = findViewById(R.id.txtRegister)
        toolbar = findViewById(R.id.toolbar)

        setUpToolbar()

        btnLogIn.setOnClickListener {
            startActivity(intent)
            etPhnNumber.text.clear()
            etPassword.text.clear()
        }

        txtRegister.setOnClickListener {
            startActivity(intentRegister)
            etPhnNumber.text.clear()
            etPassword.text.clear()
        }

        txtForgetPassword.setOnClickListener {
            startActivity(intentForgetPassword)
            etPhnNumber.text.clear()
            etPassword.text.clear()
        }

    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "LOGIN"
    }
}
