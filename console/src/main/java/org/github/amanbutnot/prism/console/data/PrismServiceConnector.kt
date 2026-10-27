package org.github.amanbutnot.prism.console.data

import android.content.ComponentName
import android.content.Context
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.github.amanbutnot.prism.core.ipc.IPrismService

class PrismServiceConnector(private val info: PrismServiceInfo, private val context: Context) {
    private var isBound = false
    private var service: IPrismService? = null
    val prismService: IPrismService?
        get() = service
    private val compName = ComponentName(info.packageName, info.serviceClassName)
    private val compIntent = Intent().setComponent(compName)

    private val _connectionState = MutableStateFlow(ConnectionState.DISCONNECTED)
    val connectionState: StateFlow<ConnectionState> = _connectionState
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(
            p0: ComponentName?,
            p1: IBinder?
        ) {
            val aidlService = IPrismService.Stub.asInterface(p1)
            service = aidlService
            _connectionState.value = ConnectionState.CONNECTED
            println("Service is connected and binder for testing is $p1")
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            service = null
            _connectionState.value = ConnectionState.DISCONNECTED
            println("service is diconnected")
        }
    }

    fun connect() {
        _connectionState.value = ConnectionState.CONNECTING
        val res = context.bindService(compIntent, serviceConnection, BIND_AUTO_CREATE)
        isBound = res
        if (!res) {
            _connectionState.value = ConnectionState.DISCONNECTED
        }

    }

    fun disconnect() {
        if (isBound) {
            context.unbindService(serviceConnection)
            _connectionState.value = ConnectionState.DISCONNECTED
            service = null
            isBound = false
        }

    }
}

enum class ConnectionState {
    DISCONNECTED, CONNECTED, CONNECTING
}