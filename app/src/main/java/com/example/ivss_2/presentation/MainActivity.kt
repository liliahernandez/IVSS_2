package com.example.ivss_2.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ivss_2.R

class MainActivity : AppCompatActivity() {

    private lateinit var statusText: TextView
    private lateinit var connectButton: Button
    private lateinit var motorButton: Button
    private lateinit var authButton: Button
    private lateinit var notifyButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusText = findViewById(R.id.statusText)
        connectButton = findViewById(R.id.connectButton)
        motorButton = findViewById(R.id.motorButton)
        authButton = findViewById(R.id.authButton)
        notifyButton = findViewById(R.id.notifyButton)

        connectButton.setOnClickListener {
            statusText.text = "Conectado al teléfono ✅"
        }

        motorButton.setOnClickListener {
            val intent = Intent(this, MotorLockActivity::class.java)
            startActivity(intent)
        }

        authButton.setOnClickListener {
            Simulator.sendAuthResponse(this)
        }

        notifyButton.setOnClickListener {
            Simulator.sendIotNotification(this, "Puerta abierta en zona A")
        }
    }
}