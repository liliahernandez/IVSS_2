package com.example.ivss_2.presentation

import android.content.Context
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.Wearable

object Simulator {

    // Simula que el teléfono responde autenticación exitosa
    fun sendAuthResponse(context: Context) {
        val client: MessageClient = Wearable.getMessageClient(context)
        Wearable.getNodeClient(context).connectedNodes
            .addOnSuccessListener { nodes ->
                for (node in nodes) {
                    client.sendMessage(
                        node.id,
                        "/auth_response",
                        "AUTH_OK".toByteArray()
                    )
                }
            }
    }

    // Simula que el teléfono/IoT envía el estado final del motor
    fun sendMotorState(context: Context, active: Boolean) {
        val client: MessageClient = Wearable.getMessageClient(context)
        val state = if (active) "ACTIVE" else "INACTIVE"
        Wearable.getNodeClient(context).connectedNodes
            .addOnSuccessListener { nodes ->
                for (node in nodes) {
                    client.sendMessage(
                        node.id,
                        "/motor_state",
                        state.toByteArray()
                    )
                }
            }
    }

    // Simula que el IoT envía una alerta al reloj
    fun sendIotNotification(context: Context, message: String) {
        val client: MessageClient = Wearable.getMessageClient(context)
        Wearable.getNodeClient(context).connectedNodes
            .addOnSuccessListener { nodes ->
                for (node in nodes) {
                    client.sendMessage(
                        node.id,
                        "/iot_notification",
                        message.toByteArray()
                    )
                }
            }
    }
}