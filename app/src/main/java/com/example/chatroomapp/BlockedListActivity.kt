package com.example.chatroomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BlockedListActivity : AppCompatActivity() {
    var database = FirebaseDatabase.getInstance().getReference("users")
    var username = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blocked_list)
        username = intent.getStringExtra("username")!!

        //Need listview to show who is already blocked

        database.child(username).child("blocked").addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var snap = dataSnapshot.children
                var blockedUsersList = arrayListOf<String>()
                for (i in snap) {
                    if(i.value == true) {
                        blockedUsersList.add(i.key.toString())
                    }
                }

                var blockedList = findViewById<ListView>(R.id.blockedList)
                var listAdapter:
                        ArrayAdapter<String> =
                    ArrayAdapter(
                        this@BlockedListActivity,
                        android.R.layout.simple_list_item_1,
                        blockedUsersList
                    )
                blockedList.adapter = listAdapter
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }
}