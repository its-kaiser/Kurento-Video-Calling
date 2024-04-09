package com.example.kurento

import com.example.kurento.models.Message
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject
import java.net.URISyntaxException

class SocketManager {

    private var socket: Socket? = null
    val SOCKET_URL = "http://192.168.230.199:3000"

    init {
        try {
            socket = IO.socket(SOCKET_URL)
            socket?.on("message"){

            }
            socket?.connect()
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
    }


    fun emitMessage(message:Message){
        val jsonMessage = JSONObject().apply {
            put("event",message.event)
            put("userName",message.userName)
            put("roomName",message.roomName)
        }

        socket!!.emit("message",jsonMessage)
    }
    fun disconnectSocket(){
        socket?.disconnect()
        socket?.off()
    }

}