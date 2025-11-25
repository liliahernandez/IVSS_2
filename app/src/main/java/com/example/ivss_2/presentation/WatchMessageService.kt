package com.example.ivss_2.presentation

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService
import com.example.ivss_2.R

class WatchMessageService : WearableListenerService() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onMessageReceived(messageEvent: MessageEvent) {
        when (messageEvent.path) {
            "/iot_notification" -> {
                val msg = String(messageEvent.data)
                showNotification("Alerta IoT", msg, 101)
            }
            "/motor_state" -> {
                val state = String(messageEvent.data)
                val message = if (state == "ACTIVE") "Motor ACTIVADO" else "Motor APAGADO"
                showNotification("Estado Motor", message, 102)
            }
        }
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private fun showNotification(title: String, body: String, id: Int) {
        val notification = NotificationCompat.Builder(this, "iot_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        NotificationManagerCompat.from(this).notify(id, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "iot_channel",
                "IoT Alerts",
                NotificationManager.IMPORTANCE_HIGH
            )
            val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nm.createNotificationChannel(channel)
        }
    }
}