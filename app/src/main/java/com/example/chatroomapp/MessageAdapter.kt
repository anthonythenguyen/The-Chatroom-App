package com.example.chatroomapp

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.firebase.database.Query
import com.google.firebase.database.core.Context
import com.google.firebase.storage.StorageReference

class MessageAdapter(context: Context, query: Query?, userID: String?) {
    private val TAG = "MessageAdaper"
    var context: Context? = null
    var userId: String? = null
    var storageReference: StorageReference? = null
//    private val requestOptions: RequestOptions = RequestOptions()
    private val MESSAGE_IN_VIEW_TYPE = 1
    private val MESSAGE_OUT_VIEW_TYPE = 2
    fun getItemViewType(position: Int): Int {
        //if message userId matches current userid, set view type 1 else set view type 2
        return if (getItem(position).messageUserId == userId) {
            MESSAGE_OUT_VIEW_TYPE
        } else MESSAGE_IN_VIEW_TYPE
    }

    private fun getItem(position: Int): Message {
        //this is junk data for now
        var mess = Message("", "", "")
        return mess
    }
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        /*
        We're using two different layouts. One for messages from others and the other for user's messages
         */
        var view: View? = null
        if (viewType == MESSAGE_IN_VIEW_TYPE) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_chat, parent, false)
        } else {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_chat, parent, false)
        }
        return MessageHolder(view!!)
    }

    protected fun onBindViewHolder(holder: MessageHolder, position: Int, model: Message) {
        //Bind values from Message to the viewHolder
        val mText = holder.mText
        val mUsername = holder.mUsername
        val mTime = holder.mTime
        val imgProfile: ImageView = holder.imgProfile
        val imgDropdown = holder.imgDropdown
        mUsername.text = model.messageUser
        mText.text = model.messageText
        mTime.text = DateFormat.format("dd MMM  (h:mm a)", model.messageTime)
    }
}