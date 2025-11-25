package com.example.ivss_2.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.ivss_2.R
import com.example.ivss_2.R.*

class MotorLockActivity : AppCompatActivity() {

    private lateinit var motorStatus: TextView
    private lateinit var motorCircle: ImageView
    private lateinit var bloquearButton: Button
    private lateinit var desbloquearButton: Button
    private lateinit var preguntaLayout: LinearLayout
    private lateinit var confirmarSiButton: Button
    private lateinit var confirmarNoButton: Button
    private lateinit var preguntaTexto: TextView

    private var motorBloqueado = false
    private var accionPendiente = "" // "bloquear" o "desbloquear"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_motor_lock)

        motorStatus = findViewById(id.motorStatus)
        motorCircle = findViewById(id.motorCircle)
        bloquearButton = findViewById(id.bloquearButton)
        desbloquearButton = findViewById(id.desbloquearButton)
        preguntaLayout = findViewById(id.preguntaLayout)
        confirmarSiButton = findViewById(id.confirmarSiButton)
        confirmarNoButton = findViewById(id.confirmarNoButton)
        preguntaTexto = findViewById(id.preguntaTexto)

        updateUI()

        bloquearButton.setOnClickListener {
            accionPendiente = "bloquear"
            mostrarPregunta()
        }

        desbloquearButton.setOnClickListener {
            accionPendiente = "desbloquear"
            mostrarPregunta()
        }

        confirmarSiButton.setOnClickListener {
            motorBloqueado = accionPendiente == "bloquear"
            updateUI()
            Toast.makeText(this,
                if (motorBloqueado) "Motor bloqueado ✅" else "Motor desbloqueado ✅",
                Toast.LENGTH_SHORT
            ).show()
        }

        confirmarNoButton.setOnClickListener {
            preguntaLayout.visibility = LinearLayout.GONE
            bloquearButton.visibility = if (!motorBloqueado) Button.VISIBLE else Button.GONE
            desbloquearButton.visibility = if (motorBloqueado) Button.VISIBLE else Button.GONE
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun mostrarPregunta() {
        preguntaTexto.text = if (accionPendiente == "bloquear")
            "¿Deseas bloquear el motor?" else "¿Deseas desbloquear el motor?"

        bloquearButton.visibility = Button.GONE
        desbloquearButton.visibility = Button.GONE
        preguntaLayout.visibility = LinearLayout.VISIBLE
    }

    private fun updateUI() {
        if (motorBloqueado) {
            motorStatus.text = "Motor BLOQUEADO"
            motorCircle.setImageResource(drawable.red_circle)
            bloquearButton.visibility = Button.GONE
            desbloquearButton.visibility = Button.VISIBLE
        } else {
            motorStatus.text = "Motor DESBLOQUEADO"
            motorCircle.setImageResource(drawable.green_circle)
            bloquearButton.visibility = Button.VISIBLE
            desbloquearButton.visibility = Button.GONE
        }

        preguntaLayout.visibility = LinearLayout.GONE
    }
}