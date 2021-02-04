package com.example.foodie.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.foodie.R

class ChangePasswordActivity : AppCompatActivity() {

    lateinit var txtPhnNumber: TextView
    lateinit var txtEmail: TextView
    lateinit var etOTP: EditText
    lateinit var etPassword: EditText
    lateinit var etConfirmPassword: EditText
    lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        val intent = Intent(this@ChangePasswordActivity, LoginActivity::class.java)

        txtPhnNumber=findViewById(R.id.txtPhnNumber)
        txtEmail=findViewById(R.id.txtEmail)
        etOTP=findViewById(R.id.etOTP)
        etPassword=findViewById(R.id.etPassword)
        etConfirmPassword=findViewById(R.id.etConfirmPassword)
        btnSave=findViewById(R.id.btnSave)

        btnSave.setOnClickListener{
            startActivity(intent)
            finish()
        }
    }
}
