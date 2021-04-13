package com.example.chatroomapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var database = FirebaseDatabase.getInstance().getReference("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        auth = Firebase.auth

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val create = findViewById<Button>(R.id.register)
        val login = findViewById<Button>(R.id.login)

        create.setOnClickListener {
            val intent = Intent(this@SignInActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener(this@SignInActivity, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@SignInActivity, "Success", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@SignInActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@SignInActivity, "Login failed", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}