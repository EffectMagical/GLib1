package com.snow.glib.ui.sign

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.snow.glib.MainActivity
import com.snow.glib.R

class SignUpActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private var pass: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        email = findViewById(R.id.edit_text_email_up)
        password = findViewById(R.id.edit_text_password_up)

        val signInBtn: TextView = findViewById(R.id.sign_in_click_text)
        val signUnBtn: Button = findViewById(R.id.up_btn)

        signUnBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        signInBtn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}