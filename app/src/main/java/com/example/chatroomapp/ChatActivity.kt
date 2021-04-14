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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.*


class ChatActivity : AppCompatActivity(){
    lateinit var auth: FirebaseAuth
    lateinit var user: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
//        var db = FirebaseFirestore.getInstance()
//        var query: Query
//        private var recyclerAdapter: FirestoreRecyclerAdapter<Message, MessageAdapter, MessageHolder>
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

    class Message(
        private var messageUser: String,
        private var messageText: String,
        private var messageUserId: String
    ) {
        private var messageTime: Long

        init {
            messageTime = Date().getTime()
            this.messageUserId = messageUserId
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

    }

    class MessageAdapter(context: Context?, textViewResourceId: Int) : ArrayAdapter<Message>(
        context!!,
        textViewResourceId
    ){

    }

    class MyAdapter(var data: Array<Message>, var context: Context): BaseAdapter() {
        override fun getCount(): Int {
            return data.size
        }

        override fun getItem(position: Int): Any {
            return position
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View, parent: ViewGroup?): View {
//            if( convertView == null ){
//                var inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
//                convertView =  inflater.inflate(R.layout.activity_chat, null)
//            }
            val myTextView1 = convertView.findViewById(R.id.user) as TextView
            myTextView1.setText(getItem(position * 3).toString())
            val myTextView2 = convertView.findViewById(R.id.mess) as TextView
            myTextView2.setText(getItem(position * 3 + 1).toString())
            val myTextView3 = convertView.findViewById(R.id.date) as TextView
            myTextView3.setText(getItem(position * 3 + 2).toString())

            return convertView
        }
    }

}