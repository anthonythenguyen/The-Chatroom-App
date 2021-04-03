package com.example.chatroomapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setSupportActionBar(findViewById(R.id.toolbar))

        var arrChat = arrayListOf<String>("stuff", "more", "other")

        var messageBox = findViewById(R.id.messageBox) as EditText
        var sendMessage = findViewById(R.id.fab) as FloatingActionButton
        var chatList = findViewById(R.id.chatList) as ListView
        var back = findViewById<Button>(R.id.back)

        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        var listAdapter: ArrayAdapter<String> =
                ArrayAdapter(this, android.R.layout.simple_list_item_1,arrChat)
        chatList.adapter = listAdapter

        sendMessage.setOnClickListener{
            if(messageBox.text.toString().trim() != ""){
                arrChat.add(messageBox.text.toString().trim())
                messageBox.setText("")
            }
        }


/*
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
*/
//        val database = FirebaseDatabase.getInstance()
//        val myRef = database.getReference("message")
//
//
//        myRef.setValue("Hello, World")
//
//
//        myRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                val value = dataSnapshot.getValue(String::class.java)
//                message("Value: $value")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Failed to read value
//                message("Failed to read value")
//            }
//        })
    }

    fun message(m: String){
        Toast.makeText(this, "$m", Toast.LENGTH_SHORT).show()
    }
}