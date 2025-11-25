package com.example.ivss_2.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ivss_2.R

class MotorLockActivity : AppCompatActivity() {

    private lateinit var motorStatus: TextView
    private lateinit var motorCircle: ImageView
    private lateinit var toggleButton: Button

    private var motorActive = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motor_lock)

        motorStatus = findViewById(R.id.motorStatus)
        motorCircle = findViewById(R.id.motorCircle)
        toggleButton = findViewById(R.id.toggleButton)

        updateUI()

        toggleButton.setOnClickListener {
            motorActive = !motorActive
            updateUI()
            // Aquí enviarías el comando al teléfono vía Data Layer API
        }
    }

    private fun updateUI() {
        if (motorActive) {
            motorStatus.text = "Motor ACTIVADO"
            motorCircle.setImageResource(R.drawable.red_circle)
            toggleButton.text = "Bloquear Motor"
        } else {
            motorStatus.text = "Motor APAGADO"
            motorCircle.setImageResource(R.drawable.green_circle)
            toggleButton.text = "Desbloquear Motor"
        }
    }
}