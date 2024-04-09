package com.example.kurento

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kurento.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            btnJoinRoom.setOnClickListener {
                val userName = etUsername.text.toString()
                val roomName = etRoomName.text.toString()

                if (userName.isEmpty() || roomName.isEmpty()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Room and Name are required",
                        Toast.LENGTH_SHORT
                    ).show()
                }else{
                    val intent = Intent(this@MainActivity, VideoCalling::class.java)
                    intent.putExtra("userName",userName)
                    intent.putExtra("roomName",roomName)
                    startActivity(intent)
                }
            }
        }
    }
}