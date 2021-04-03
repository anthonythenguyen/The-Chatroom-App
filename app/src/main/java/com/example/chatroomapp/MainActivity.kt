package com.example.chatroomapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        auth = FirebaseAuth.getInstance()
//        Firebase.auth.signOut()

        if(auth.currentUser == null){
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }else{
//            Toast.makeText(this, "Already logged in", Toast.LENGTH_LONG).show()
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()

            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }
    }
}