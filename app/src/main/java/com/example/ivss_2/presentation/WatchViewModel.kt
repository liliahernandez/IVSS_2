package com.example.ivss_2.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class WatchViewModel(app: Application) : AndroidViewModel(app) {

    var isConnected: Boolean = false
        private set

    var motorActive: Boolean = false
        private set

    private val messageClient: MessageClient by lazy {
        Wearable.getMessageClient(getApplication())
    }

    fun tryConnectToPhone() {
        viewModelScope.launch(Dispatchers.IO) {
            val nodes = Wearable.getNodeClient(getApplication()).connectedNodes.await()
            isConnected = nodes.isNotEmpty()
        }
    }

    fun toggleMotor() {
        viewModelScope.launch(Dispatchers.IO) {
            val nodes = Wearable.getNodeClient(getApplication()).connectedNodes.await()
            val command = if (motorActive) "LOCK" else "UNLOCK"
            for (node in nodes) {
                messageClient.sendMessage(node.id, "/motor_command", command.toByteArray()).await()
            }
        }
        motorActive = !motorActive
    }
}
