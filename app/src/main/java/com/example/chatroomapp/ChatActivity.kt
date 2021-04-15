package com.example.chatroomapp

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.database.FirebaseListAdapter
import com.firebase.ui.database.FirebaseListOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*


class ChatActivity : AppCompatActivity(){
    lateinit var auth: FirebaseAuth
    var database = FirebaseDatabase.getInstance().getReference("users")
    var user = "thisiszoe"
    var otherUser = "ajallen10"

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setSupportActionBar(findViewById(R.id.toolbar))

//        var userMessageBox = findViewById(R.id.chatList) as RecyclerView
        var messageBox = findViewById(R.id.messageBox) as EditText
        var sendMessage = findViewById(R.id.fab) as FloatingActionButton

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        else {
            Toast.makeText(this, "Here", Toast.LENGTH_SHORT).show()
        }

        var arrChat = arrayListOf<Message>()
        user = intent.getStringExtra("user")!!
        otherUser = intent.getStringExtra("other")!!

//        database.child(user).child("conversations").addValueEventListener(object :
//            ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                var snap = dataSnapshot.children
//                for (i in snap) {
//                    val data: String? = i.key
//                    print("1st")
//                    if (data != null && data == otherUser) {
//                        var messageNum = 0
//                        for (j in i.children) {
//                            print("2nd")
//                            if (j.key != null && j.key != "messageNum") {
//                                var m = Message(
//                                    j.child("username").value.toString(),
//                                    j.child("message").value.toString(),
//                                    j.child("time").value.toString()
//                                )
//                                arrChat.add(m)
//                            } else {
////                                messageNum = j.value as Int
//                            }
//                        }
//                    }
//                }

                var chatList = findViewById(R.id.chatList) as ListView

                var query: Query = FirebaseDatabase.getInstance()
                    .getReference("users/$user/conversations/$otherUser")
                    .orderByKey()

                var options = FirebaseListOptions.Builder<Message>()
                    .setLayout(R.layout.activity_chat) //Note: The guide doesn't mention this method, without it an exception is thrown that the layout has to be set.
                    .setQuery(query, Message::class.java)
                    .build()

                var adapter: FirebaseListAdapter<*> = object : FirebaseListAdapter<Message>(options) {
                    override fun populateView(v: View, model: Message, position: Int) {
                        // Get references to the views of message.xml
                        val messageText = v.findViewById<View>(R.id.user) as TextView
                        val messageUser = v.findViewById<View>(R.id.mess) as TextView
                        val messageTime = v.findViewById<View>(R.id.date) as TextView

                        // Set their text
                        messageText.setText(model.getMessageText())
                        messageUser.setText(model.getMessageUser())
                        messageTime.setText(model.getMessageTime())
                    }
                }

                chatList.adapter = adapter
//            }

//            override fun onCancelled(databaseError: DatabaseError) {}
//        })

        sendMessage.setOnClickListener{
            Toast.makeText(this, "Here", Toast.LENGTH_SHORT).show()
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

        //member functions
        fun getMessageUser():String{
            return messageUser;
        }

        fun setMessageUser(messageUser: String) :Unit {
            this.messageUser = messageUser
        }

        fun getMessageText(): String? {
            return messageText
        }

        fun setMessageText(messageText: String?) {
            this.messageText = messageText!!
        }

        fun getMessageTime(): String {
            return messageTime
        }

        fun setMessageTime(messageTime: String) {
            this.messageTime = messageTime
        }

    }

}