package com.example.kurento.utils

import com.example.kurento.models.Message

interface NewMessageInterface {
    fun  onNewMessage(message:Message)
}