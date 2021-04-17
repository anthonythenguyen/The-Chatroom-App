package com.example.chatroomapp

import android.content.Intent
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
    var database = FirebaseDatabase.getInstance().getReference("users")
    var username = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_chat)
        auth = FirebaseAuth.getInstance()

        var search = findViewById<Button>(R.id.search)
        var searchBar = findViewById<EditText>(R.id.searchBar)
        var result = findViewById<TextView>(R.id.result)
        var chat = findViewById<Button>(R.id.addFriend)
        chat.isEnabled = false

        search.setOnClickListener {
            database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var snap = dataSnapshot.children
                    for (i in snap) {
                        val data: String? = i.key
                        if (data != null && data == searchBar.text.toString()) {
                            result.setText(data)
                            searchBar.setText("")
                            chat.isEnabled = true
                        }

                        for (j in i.children) {
                            if (data != null && j.value == auth.currentUser?.uid) {
                                username = i.key.toString()
                            }
                        }
                    }
                    if(result.text.toString() == ""){
                        result.setText("User does not exist")
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

        }

        chat.setOnClickListener {
            var exists = false
            var ref = database.child(username).child("conversations")
            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var snap = dataSnapshot.children
                    for (i in snap) {
                        val data: String? = i.key
                        if (data != null && data == result.text.toString()) {
                            exists = true
                            result.setText("Conversation with this user already exists (user may be blocked)")
                            chat.isEnabled = false
                        }
                    }
                    if(!exists){
                        database.child(username).child("blocked").child(result.text.toString()).setValue(false)
                        database.child(result.text.toString()).child("blocked").child(username).setValue(false)
                        var intent = Intent(this@NewChatActivity, ChatActivity::class.java)
                        intent.putExtra("user", username)
                        intent.putExtra("other", result.text.toString())
                        startActivity(intent)
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