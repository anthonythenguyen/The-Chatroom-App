package com.example.chatroomapp

import java.util.*

class Message(
    var messageUser: String,
    var messageText: String,
    messageUserId: String
) {
    var messageUserId: String
    var messageTime: Long

    init {
        messageTime = Date().time
        this.messageUserId = messageUserId
    }
}