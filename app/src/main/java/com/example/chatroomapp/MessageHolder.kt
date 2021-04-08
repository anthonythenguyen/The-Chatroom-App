package com.example.chatroomapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var mText: TextView
    var mUsername: TextView
    var mTime: TextView
    var mLikesCount: TextView
    var imgProfile: ImageView
    var imgDropdown: ImageView
    var imgLikes: ImageView

    init {
        // TODO add the proper IDs here
        mText = itemView.findViewById(R.id.dark)
        mUsername = itemView.findViewById(R.id.login)
        mTime = itemView.findViewById(R.id.chatList)
        mLikesCount = itemView.findViewById(R.id.register)
        imgProfile = itemView.findViewById(R.id.emailReg)
        imgLikes = itemView.findViewById(R.id.email)
        imgDropdown = itemView.findViewById(R.id.username)
    }
}