package com.example.kurento

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kurento.models.Message

class VideoCalling : AppCompatActivity() {

    private lateinit var socketManager: SocketManager
    private lateinit var userName: String
    private lateinit var roomName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_video_calling)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        socketManager = SocketManager()
        userName = intent.getStringExtra("userName").toString()
        roomName = intent.getStringExtra("roomName").toString()
        joinRoom(userName,roomName)
    }

    private fun joinRoom(userName: String, roomName: String) {
        val message = Message(
            event = "joinRoom",
            userName = userName,
            roomName = roomName
        )
        socketManager.emitMessage(message)
    }

    override fun onDestroy() {
        socketManager.disconnectSocket()
        super.onDestroy()
    }
}