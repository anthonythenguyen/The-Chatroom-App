package com.example.chatroomapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setSupportActionBar(findViewById(R.id.toolbar))
        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        else {
            Toast.makeText(this, "Here", Toast.LENGTH_SHORT).show()
        }

        var arrChat = arrayListOf<String>("stuff", "more", "other")

        var messageBox = findViewById(R.id.messageBox) as EditText
        var sendMessage = findViewById(R.id.fab) as FloatingActionButton
        var chatList = findViewById(R.id.chatList) as ListView
//        var back = findViewById<Button>(R.id.back)

//        back.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }

        var listAdapter: ArrayAdapter<String> =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, arrChat)
        chatList.adapter = listAdapter

        sendMessage.setOnClickListener{
            Toast.makeText(this, "Here", Toast.LENGTH_SHORT).show()
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
}