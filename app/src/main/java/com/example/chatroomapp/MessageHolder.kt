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
        mText = itemView.findViewById(R.id.back)
        mUsername = itemView.findViewById(R.id.button_first)
        mTime = itemView.findViewById(R.id.First2Fragment)
        mLikesCount = itemView.findViewById(R.id.login)
        imgProfile = itemView.findViewById(R.id.SecondFragment)
        imgLikes = itemView.findViewById(R.id.Second2Fragment)
        imgDropdown = itemView.findViewById(R.id.username)
    }
}