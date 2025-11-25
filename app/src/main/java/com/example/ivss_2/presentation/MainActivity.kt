package com.example.ivss_2.presentation

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ivss_2.R

class MainActivity : AppCompatActivity() {

    private lateinit var statusText: TextView
    private lateinit var conectarButton: Button
    private lateinit var motorLockButton: Button

    private var conectado = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusText = findViewById(R.id.statusText)
        conectarButton = findViewById(R.id.conectarButton)
        motorLockButton = findViewById(R.id.motorLockButton)

        // Estado inicial
        statusText.text = "Celular no encontrado"
        statusText.setTextColor(Color.RED)

        conectarButton.setOnClickListener {
            if (!conectado) {
                statusText.text = "Se conectó al celular ✅"
                statusText.setTextColor(Color.parseColor("#008000")) // verde
                conectado = true
            }
        }

        motorLockButton.setOnClickListener {
            val intent = Intent(this, MotorLockActivity::class.java)
            startActivity(intent)
        }
    }
}