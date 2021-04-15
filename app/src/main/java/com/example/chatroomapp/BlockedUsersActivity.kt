package com.example.chatroomapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BlockedUsersActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    var database = FirebaseDatabase.getInstance().getReference("users")
    var username = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blocked_users)
        auth = FirebaseAuth.getInstance()

        var searchBarText = findViewById<EditText>(R.id.searchBar21)
        var searchBtn = findViewById<Button>(R.id.search21)
        var blockedList = findViewById<ListView>(R.id.blockedList)
        var addBtn = findViewById<Button>(R.id.addBtn)
        var removeBtn = findViewById<Button>(R.id.removeBtn)
        var result21 = findViewById<TextView>(R.id.result21)
        var listOfBlocked = findViewById<TextView>(R.id.listOfBlocked)


        //Need this to search database for that username and store that username

//
//        searchBtn.setOnClickListener {
//            database.addValueEventListener(object : ValueEventListener {
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//                    var snap = dataSnapshot.children
//                    for (i in snap) {
//                        val data: String? = i.key
//                        if (data != null && data == searchBarText.text.toString()) {
//                            result21.setText(data)
//                            searchBarText.setText("")
//
//                        }
//
//                        if (result21.text.toString().equals("")) {
//                            result21.setText("User does not exist")
//                        }
//                    }
//
//
//                }
//
//                override fun onCancelled(databaseError: DatabaseError) {}
//            })
//        }
//        }


         //Need this to block the given username

        /*
        addBtn.setOnClickListener{
            var ref = database.child(username).child("conversations").child("blocked")
            ref.addValueEventListener(object : ValueEventListener {


                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var snap = dataSnapshot.children
                    for (i in snap) {
                        val data: String? = i.key
                        if (data != null && data == "False") {

                            //Set the data to "True"
                        }
                        if(data != null && data == "True"){
                            //Toast.makeText(this, "This user is already blocked", Toast.LENGTH_SHORT).show()
                        }

                        }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

        }
        */


         //Need this to unblock the given username

        /*
        removeBtn.setOnClickListener{
            var ref = database.child(username).child("conversations").child("blocked")
            ref.addValueEventListener(object : ValueEventListener {


                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var snap = dataSnapshot.children
                    for (i in snap) {
                        val data: String? = i.key
                        if (data != null && data == "False") {


                            //Toast.makeText(this, "This user is already not blocked", Toast.LENGTH_SHORT).show()
                        }
                        if(data != null && data == "True"){

                            //Set the data to "False"
                    }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

        }
        */


        //Need listview to show who is already blocked




    }
}