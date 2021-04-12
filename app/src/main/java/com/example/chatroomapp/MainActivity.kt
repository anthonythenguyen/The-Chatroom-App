package com.example.chatroomapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    var database = FirebaseDatabase.getInstance().getReference("users")
    var username = ""
    private var mTopToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        auth = FirebaseAuth.getInstance()
        if(auth.currentUser == null){
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var snap = dataSnapshot.children
                for (i in snap) {
                    val data: String? = i.key
                    for (j in i.children) {
                        if (data != null && j.value == auth.currentUser?.uid) {
                            username = i.key.toString()
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })

        var listOfCurrentConvos = arrayListOf<String>()
        var ref = database.child(username).child("conversations")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var snap = dataSnapshot.children

                for (i in snap) {
                    val data: String? = i.key
                    listOfCurrentConvos.add(data.toString())
                }

                var myList = findViewById(R.id.myListView) as ListView
                var listAdapter: ArrayAdapter<String> =
                    ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, listOfCurrentConvos)
                myList.adapter = listAdapter
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })



        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()

            val intent = Intent(this@MainActivity, NewChatActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.right_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        if (id == R.id.settings) {
            startActivity(Intent(this, SettingsActivity::class.java))
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}