package com.example.chatroomapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
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

        var searchBar2 = findViewById<EditText>(R.id.searchBar2)

        var searchBtn = findViewById<Button>(R.id.search21)
        var blockedList = findViewById<ListView>(R.id.blockedList)
        var addBtn = findViewById<Button>(R.id.addBtn)
        var removeBtn = findViewById<Button>(R.id.removeBtn)
        var result21 = findViewById<TextView>(R.id.result21)
        var listOfBlocked = findViewById<TextView>(R.id.listOfBlocked)


        //Need this to search database for that username and store that username

        searchBtn.setOnClickListener {
            database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var snap = dataSnapshot.children

                    for (i in snap) {
                        val data: String? = i.key
                        if (data != null && data == searchBar2.text.toString()) {
                            result21.setText(data)
                            searchBar2.setText("")

                            username = i.key.toString()
                        }


                    }

                        if (result21.text.toString().equals("")) {
                            result21.setText("User does not exist")
                        }
                    }




                override fun onCancelled(databaseError: DatabaseError) {}
            })
        }




         //Need this to block the given username

//        addBtn.setOnClickListener{
//            var ref = database.child(username).child("blocked")
//            ref.addValueEventListener(object : ValueEventListener {
//
//
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//                    var snap = dataSnapshot.children
//                    for (i in snap) {
//                        var data: String? = i.key
//                        if (data != null && data.equals("False")) {
//
//
//                            //Set the data to "True"
//                            data = "True"
//                        }
//                        if(data != null && data == "True"){
//                            var input = "This user is already blocked"
//                            Toast.makeText(this@BlockedUsersActivity, input, Toast.LENGTH_SHORT).show()
//                        }
//
//                        }
//                }
//
//                override fun onCancelled(databaseError: DatabaseError) {}
//            })
//
//        }
//
//
//
//         //Need this to unblock the given username
//
//        removeBtn.setOnClickListener{
//            var ref = database.child(username).child("blocked")
//            ref.addValueEventListener(object : ValueEventListener {
//
//
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//                    var snap = dataSnapshot.children
//                    for (i in snap) {
//                        var data: String? = i.key
//                        if (data != null && data == "False") {
//
//                            var input = "This user is already unblocked"
//                            Toast.makeText(this@BlockedUsersActivity, input, Toast.LENGTH_SHORT).show()
//                        }
//
//                        if (data != null && data == "True") {
//
//                            //Set the data to "False"
//                            data = "False"
//                        }
//
//                    }
//                }
//
//                override fun onCancelled(databaseError: DatabaseError) {}
//            })
//
//        }
//


        //Need listview to show who is already blocked

//        database.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                var snap = dataSnapshot.children
//                for (i in snap) {
//                    val data: String? = i.key
//                    for (j in i.children) {
//                        if (data != null && j.value == auth.currentUser?.uid) {
//                            username = i.key.toString()
//
//                            var blockedUsersList = arrayListOf<String>()
//                            var ref = database.child(username).child("blocked")
//
//                            ref.addValueEventListener(object : ValueEventListener {
//                                override fun onDataChange(dataSnapshot: DataSnapshot) {
//                                    var snap = dataSnapshot.children
//                                    for (i in snap) {
//                                        val data: String? = i.key
//                                        blockedUsersList.add(data.toString())
//                                    }
//
//                                    var myList = findViewById(R.id.myListView) as ListView
//                                    var listAdapter: ArrayAdapter<String> =
//                                            ArrayAdapter(
//                                                    this@BlockedUsersActivity,
//                                                    android.R.layout.simple_list_item_1,
//                                                    blockedUsersList
//                                            )
//                                    myList.adapter = listAdapter
//
//
//                                }
//
//                                override fun onCancelled(databaseError: DatabaseError) {}
//                            })
//                        }
//                    }
//                }
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {}
//        })


    }

}