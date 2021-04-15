package com.example.chatroomapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


        var logout = findViewById<Button>(R.id.logout)
        var blockButton = findViewById<Button>(R.id.blockBtn)

        logout.setOnClickListener {
            var alert = AlertDialog.Builder(this)
            alert.setTitle("Logging out")
            alert.setMessage("Are you sure you want to log out?")
            alert.setPositiveButton("Yes",{ dialogInterface: DialogInterface, i: Int ->
                Firebase.auth.signOut()
//                finish()
                startActivity(Intent(this, SignInActivity::class.java))
            })
            alert.setNegativeButton("No",{ dialogInterface: DialogInterface, i: Int -> })
            alert.show()
        }


        blockButton.setOnClickListener {
            val intent = Intent(this, BlockedUsersActivity::class.java)
            startActivity(intent)
        }

        }

    }
