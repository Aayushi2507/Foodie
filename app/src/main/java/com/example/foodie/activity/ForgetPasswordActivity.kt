package com.example.foodie.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import com.example.foodie.R

class ForgetPasswordActivity : AppCompatActivity() {

    lateinit var etPhnNumber: EditText
    lateinit var etEmail: EditText
    lateinit var btnNext: Button
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        val intent = Intent(this@ForgetPasswordActivity, ChangePasswordActivity::class.java)

        etPhnNumber = findViewById(R.id.etPhnNumber)
        etEmail = findViewById(R.id.etEmail)
        btnNext = findViewById(R.id.btnNext)
        toolbar = findViewById(R.id.toolbar)
        setUpToolbar()

        btnNext.setOnClickListener{
            startActivity(intent)
            etPhnNumber.text.clear()
            etEmail.text.clear()
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "RESET PASSWORD"
    }
}
