package com.example.chatroomapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class BlockedUsersActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    var database = FirebaseDatabase.getInstance().getReference("users")
    var username = ""
    var userBlock = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blocked_users)
        auth = FirebaseAuth.getInstance()
        username = intent.getStringExtra("username")!!

        var searchBar2 = findViewById<EditText>(R.id.searchBar2)
        var searchBtn = findViewById<Button>(R.id.search21)
        var addBtn = findViewById<Button>(R.id.addBtn)
        var buttonList = findViewById<Button>(R.id.buttonList)
        var result21 = findViewById<TextView>(R.id.result21)
        addBtn.isEnabled = false

        //Need this to search database for that username and store that username

        searchBtn.setOnClickListener {
            database.child(username).child("blocked").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var snap = dataSnapshot.children
                    var found = false

                    for (i in snap) {
                        val data: String? = i.key
                        if (data != null && data == searchBar2.text.toString()) {
                            found = true
                            result21.setText(data)
                            searchBar2.setText("")
                            userBlock = data.toString()

                            if(i.value == false){
                                addBtn.setText("Block")
                            }
                            else{
                                addBtn.setText("Unblock")
                            }

                            addBtn.isEnabled = true
                        }
                    }

                    if (!found) {
                        result21.setText("You don't have any conversations with this user")
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
        }

         //Need this to block the given username

        addBtn.setOnClickListener{
            var ref = database.child(username).child("blocked")
            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var snap = dataSnapshot.children
                    for (i in snap) {
                        val data: String? = i.key
                        if (data != null && data == userBlock) {
                            if(i.value == false){
                                ref.child(userBlock).setValue(true)
                                result21.setText("$userBlock is now blocked")
                            }
                            else {
                                ref.child(userBlock).setValue(false)
                                result21.setText("$userBlock is no longer blocked")
                            }
                        }

                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

            addBtn.isEnabled = false
        }


        buttonList.setOnClickListener {
            database.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var snap = dataSnapshot.children
                    for (i in snap) {
                        val data: String? = i.key
                        for (j in i.children) {
                            if (data != null && j.value == auth.currentUser?.uid) {
                                username = i.key.toString()
                                val intent = Intent(
                                    this@BlockedUsersActivity,
                                    BlockedListActivity::class.java
                                )
                                intent.putExtra("username", username)
                                startActivity(intent)
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        }

    }

}