package com.example.chatroomapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.database.FirebaseListAdapter
import com.firebase.ui.database.FirebaseListOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*


class ChatActivity : AppCompatActivity(){
    lateinit var auth: FirebaseAuth
    var user = ""
    var otherUser = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setSupportActionBar(findViewById(R.id.toolbar))

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        user = intent.getStringExtra("user")!!
        otherUser = intent.getStringExtra("other")!!
        var chatList = findViewById(R.id.chatList) as ListView

        var query: Query = FirebaseDatabase.getInstance()
            .getReference("users/$user/conversations/$otherUser")
            .orderByKey()

        var options = FirebaseListOptions.Builder<Message>()
            .setLayout(R.layout.message) //Note: The guide doesn't mention this method, without it an exception is thrown that the layout has to be set.
            .setQuery(query, Message::class.java)
            .build()

        var adapter: FirebaseListAdapter<*> = object : FirebaseListAdapter<Message>(options) {
            override fun populateView(v: View, model: Message, position: Int) {
                // Get references to the views of message.xml
                if(v != null) {
                    val messageText = v.findViewById<View>(R.id.message_user) as TextView
                    val messageUser = v.findViewById<View>(R.id.message_text) as TextView
                    val messageTime = v.findViewById<View>(R.id.message_time) as TextView

                    // Set their text
                    messageUser.setText(model.getMessageUser())
                    messageText.setText(model.getMessageText())
                    messageTime.setText(model.getMessageTime())
                }
            }
        }

        chatList.adapter = adapter
        adapter.startListening()

        var sendMessage = findViewById<Button>(R.id.fab)
        sendMessage.setOnClickListener{
            Toast.makeText(this, "Here", Toast.LENGTH_SHORT).show()
            val input = findViewById<View>(R.id.messageBox) as EditText
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm")
            val currentDate = sdf.format(Date())
            var m = Message(
                user,
                input.text.toString(),
                currentDate
            )

            FirebaseDatabase.getInstance().getReference("users").child(user).child("conversations").child(otherUser)
                .push()
                .setValue(
                    m
                )

            FirebaseDatabase.getInstance().getReference("users").child(otherUser).child("conversations").child(user)
                .push()
                .setValue(
                    m
                )

            input.setText("")
        }
    }

    fun message(m: String){
        Toast.makeText(this, "$m", Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    class Message(
        private var messageUser: String,
        private var messageText: String,
        private var messageTime: String
    ) {

        constructor() : this("", "", "")

        //member functions
        fun getMessageUser():String{
            return messageUser;
        }

        fun getMessageText(): String? {
            return messageText
        }

        fun getMessageTime(): String {
            return messageTime
        }

    }

}