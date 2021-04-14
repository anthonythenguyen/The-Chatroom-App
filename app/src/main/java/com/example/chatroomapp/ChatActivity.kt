package com.example.chatroomapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.util.*
import kotlin.collections.HashMap


class ChatActivity : AppCompatActivity(){
    lateinit var auth: FirebaseAuth
    lateinit var user: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        var db = FirebaseFirestore.getInstance()
        var query: Query
        private var recyclerAdapter: FirestoreRecyclerAdapter<Message, MessageAdapter, MessageHolder>
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setSupportActionBar(findViewById(R.id.toolbar))
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        else {
            Toast.makeText(this, "Here", Toast.LENGTH_SHORT).show()
        }

        var arrChat = arrayListOf<String>("stuff", "more", "other")
        //var arrChat = arrayListOf<ListView>()
        //var userMessageBox = findViewById(R.id.chatList) as ListView
        //arrChat.add(userMessageBox)
        var messageBox = findViewById(R.id.messageBox) as EditText
        var sendMessage = findViewById(R.id.fab) as FloatingActionButton
        //var chatList = findViewById(R.id.chatList) as ListView

//        var back = findViewById<Button>(R.id.back)

//        back.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }

        //Was <String>, changed to ListView
/*        var listAdapter: ArrayAdapter<String> =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, arrChat)
        chatList.adapter = listAdapter

        sendMessage.setOnClickListener{
            Toast.makeText(this, "Here", Toast.LENGTH_SHORT).show()
            if(messageBox.text.toString().trim() != ""){
                arrChat.add(messageBox.text.toString().trim())
                messageBox.setText("")
            }
        }*/


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
//
    }

    fun message(m: String){
        Toast.makeText(this, "$m kjk", Toast.LENGTH_SHORT).show()
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

    class Message(private var messageUser: String, private var messageText: String, private var messageUserId: String, private var messageLikesCount: Long, private var messageLikes: Map<String, Boolean>) {
        private var messageTime: Long

        init {
            messageTime = Date().getTime()
            this.messageUserId = messageUserId
            this.messageLikesCount = messageLikesCount
            this.messageLikes = messageLikes
        }

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

        fun getMessageUserId(): String? {
            return messageUserId
        }

        fun setMessageUserId(messageUserId: String?) {
            this.messageUserId = messageUserId!!
        }

        fun getMessageTime(): Long {
            return messageTime
        }

        fun setMessageTime(messageTime: Long) {
            this.messageTime = messageTime
        }

        fun getMessageLikesCount(): Long {
            return messageLikesCount
        }

        fun setMessageLikesCount(messageLikesCount: Long) {
            this.messageLikesCount = messageLikesCount
        }

        fun getMessageLikes(): Map<String, Boolean>? {
            return messageLikes
        }

        fun setMessageLikes(messageLikes: Map<String, Boolean>) {
            this.messageLikes = messageLikes
        }
    }

}