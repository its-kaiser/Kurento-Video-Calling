package com.example.kurento

import com.example.kurento.models.Message
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class SocketManager {

    private var socket: Socket? = null
    val SOCKET_URL = "http://192.168.230.199:3000"

    init {
        try {
            socket = IO.socket(SOCKET_URL)
            socket?.connect()
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
    }
    fun emitMessage(message:Message){
        val jsonMessage = Gson().toJson(message,Message::class.java)
        socket!!.emit("message",jsonMessage)
    }
    fun disconnectSocket(){
        socket?.disconnect()
        socket?.off()
    }

}