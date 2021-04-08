package com.example.chatroomapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class NewChatActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_chat)
        auth = FirebaseAuth.getInstance()

        var search = findViewById<Button>(R.id.search)
        var searchBar = findViewById<EditText>(R.id.searchBar)
        var result = findViewById<TextView>(R.id.result)

        search.setOnClickListener {
            val db = FirebaseDatabase.getInstance()
            val ref = db.getReference("users")
            val userId = auth.currentUser?.uid

            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var snap = dataSnapshot.children
                    for (i in snap) {
                        val data: String? = i.key
                        if (data != null && data == searchBar.text.toString()) {
                            message(data)
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

        }
    }

    fun message(m: String){
        Toast.makeText(this, "$m", Toast.LENGTH_SHORT).show()
    }
}