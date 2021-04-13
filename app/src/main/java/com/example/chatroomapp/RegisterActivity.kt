package com.example.chatroomapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    var database = FirebaseDatabase.getInstance().getReference("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth

        val email = findViewById<EditText>(R.id.emailReg)
        val password = findViewById<EditText>(R.id.passwordReg)
        val create = findViewById<Button>(R.id.createReg)

        create.setOnClickListener {
            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this@RegisterActivity, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@RegisterActivity, "Success", Toast.LENGTH_LONG).show()
                    createNewUser(email.text.toString(), auth.currentUser.uid)
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@RegisterActivity, "User creation failed", Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun createNewUser(email: String, uid: String?) {
        if(uid == null){
            Toast.makeText(this@RegisterActivity, "UID null", Toast.LENGTH_LONG).show()
        }
        else {
            val username = findViewById<EditText>(R.id.username)
            val user = User(uid, email)
            database.child(username.text.toString()).setValue(user)
        }
    }
}