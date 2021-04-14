package com.example.chatroomapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*


class ChatActivity : AppCompatActivity(){
    lateinit var auth: FirebaseAuth
    var database = FirebaseDatabase.getInstance().getReference("users")
    var user = ""
    var otherUser = ""

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

        database.child(user).child("conversations").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var snap = dataSnapshot.children
                for (i in snap) {
                    val data: String? = i.key
                    if (data != null && data == otherUser) {
                        var messageNum = 0
                        for(j in i.children){
                            if(j.key != "messageNum"){
                                var m = Message(j.child("username").value.toString(), j.child("message").value.toString(), j.child("time").value.toString())
                                arrChat.add(m)
                            }
                            else {
//                                messageNum = j.value as Int
                            }
                        }
                    }
                }

                var chatList = findViewById(R.id.chatList) as ListView
                var listAdapter = MyAdapter(arrChat, this@ChatActivity)
                chatList.adapter = listAdapter
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })

        //Was <String>, changed to ListView

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

    class MyAdapter(var data: ArrayList<Message>, var context: Context): BaseAdapter() {
        override fun getCount(): Int {
            return data.size
        }

        override fun getItem(position: Int): Any {
            return position
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            if( convertView != null ){
                val myTextView1 = convertView.findViewById(R.id.user) as TextView
                myTextView1.setText(getItem(position * 3).toString())
                val myTextView2 = convertView.findViewById(R.id.mess) as TextView
                myTextView2.setText(getItem(position * 3 + 1).toString())
                val myTextView3 = convertView.findViewById(R.id.date) as TextView
                myTextView3.setText(getItem(position * 3 + 2).toString())
            }

            return convertView
        }
    }

}