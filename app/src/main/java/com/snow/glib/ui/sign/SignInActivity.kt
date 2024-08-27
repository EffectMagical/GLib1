package com.snow.glib.ui.sign

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.snow.glib.MainActivity
import com.snow.glib.R

class SignInActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private var pass: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        email = findViewById(R.id.edit_text_email)
        password = findViewById(R.id.edit_text_password)
        val signInBtn: Button = findViewById(R.id.in_btn)



        signInBtn.setOnClickListener {
            pass = true
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val status = sh.getBoolean("status", false)
        val emailLocal = sh.getString("email", "")
        val passwordLocal = sh.getString("password", "")

        if (status){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        else {
            email.setText(emailLocal)
            password.setText(passwordLocal)}

    }

    override fun onPause() {
        super.onPause()

        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()

        myEdit.putString("email", email.text.toString())
        myEdit.putString("password", password.text.toString())
        myEdit.putBoolean("status", pass)
        myEdit.apply()
    }
}