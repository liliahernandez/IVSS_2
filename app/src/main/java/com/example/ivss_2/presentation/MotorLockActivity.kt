package com.example.ivss_2.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.ivss_2.R

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
    private var conectado = false // estado de conexión recibido

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motor_lock)

        // Referencias UI
        motorStatus = findViewById(R.id.motorStatus)
        motorCircle = findViewById(R.id.motorCircle)
        bloquearButton = findViewById(R.id.bloquearButton)
        desbloquearButton = findViewById(R.id.desbloquearButton)
        preguntaLayout = findViewById(R.id.preguntaLayout)
        confirmarSiButton = findViewById(R.id.confirmarSiButton)
        confirmarNoButton = findViewById(R.id.confirmarNoButton)
        preguntaTexto = findViewById(R.id.preguntaTexto)

        // Recibe estado de conexión desde MainActivity
        conectado = intent.getBooleanExtra("conectado", false)

        updateUI()

        // Botón Bloquear
        bloquearButton.setOnClickListener {
            accionPendiente = "bloquear"
            mostrarPregunta()
        }

        // Botón Desbloquear
        desbloquearButton.setOnClickListener {
            accionPendiente = "desbloquear"
            mostrarPregunta()
        }

        // Botón Sí
        confirmarSiButton.setOnClickListener {
            motorBloqueado = accionPendiente == "bloquear"
            updateUI()
            Toast.makeText(
                this,
                if (motorBloqueado) "Motor bloqueado ✅" else "Motor desbloqueado ✅",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Botón No → regresa a MainActivity manteniendo conexión
        confirmarNoButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("conectado", conectado) // mantiene estado
            startActivity(intent)
        }
    }

    // Muestra la pregunta y botones Sí/No
    private fun mostrarPregunta() {
        preguntaTexto.text = if (accionPendiente == "bloquear")
            "¿Deseas bloquear el motor?" else "¿Deseas desbloquear el motor?"

        bloquearButton.visibility = Button.GONE
        desbloquearButton.visibility = Button.GONE
        preguntaLayout.visibility = LinearLayout.VISIBLE
    }

    // Actualiza la UI según estado del motor
    private fun updateUI() {
        if (motorBloqueado) {
            motorStatus.text = "Motor BLOQUEADO"
            motorCircle.setImageResource(R.drawable.red_circle)
            bloquearButton.visibility = Button.GONE
            desbloquearButton.visibility = Button.VISIBLE
        } else {
            motorStatus.text = "Motor DESBLOQUEADO"
            motorCircle.setImageResource(R.drawable.green_circle)
            bloquearButton.visibility = Button.VISIBLE
            desbloquearButton.visibility = Button.GONE
        }

        // Oculta la pregunta al actualizar
        preguntaLayout.visibility = LinearLayout.GONE
    }
}