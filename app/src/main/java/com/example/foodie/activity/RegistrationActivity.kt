package com.example.foodie.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import com.example.foodie.R

class RegistrationActivity : AppCompatActivity() {

    lateinit var etName: EditText
    lateinit var etEmail: EditText
    lateinit var etMobNumber: EditText
    lateinit var etAddress: EditText
    lateinit var etPassword: EditText
    lateinit var etConfirmPassword: EditText
    lateinit var btnRegister: Button
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val intent = Intent(this@RegistrationActivity, LoginActivity::class.java)

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etMobNumber = findViewById(R.id.etMobNumber)
        etAddress = findViewById(R.id.etAddress)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnRegister = findViewById(R.id.btnRegister)
        toolbar = findViewById(R.id.toolbar)
        setUpToolbar()

        btnRegister.setOnClickListener{
            startActivity(intent)
            finish()
        }

    }
    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "CREATE ACCOUNT"
    }
}
