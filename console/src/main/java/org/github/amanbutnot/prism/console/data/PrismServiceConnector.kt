package org.github.amanbutnot.prism.console.data

import android.content.ComponentName
import android.content.Context
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder

class PrismServiceConnector(private val info: PrismServiceInfo, private val context: Context) {
    private var isBound = false
    private var binder: IBinder? = null
    private val compName = ComponentName(info.packageName, info.serviceClassName)
    private val compIntent = Intent().setComponent(compName)


    var connectionState: ConnectionState = ConnectionState.DISCONNECTED;
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(
            p0: ComponentName?,
            p1: IBinder?
        ) {
            binder = p1
            connectionState = ConnectionState.CONNECTED
            println("Service is connected and binder for testing is $p1")
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            binder = null
            connectionState = ConnectionState.DISCONNECTED
            println("service is diconnected")
        }
    }

    fun connect() {
        connectionState = ConnectionState.CONNECTING
        val res = context.bindService(compIntent, serviceConnection, BIND_AUTO_CREATE)
        isBound = res
        if (!res) {
            connectionState = ConnectionState.DISCONNECTED
        }

    }

    fun disconnect() {
        if (isBound) {
            context.unbindService(serviceConnection)
            connectionState = ConnectionState.DISCONNECTED
            binder = null
            isBound = false
        }

    }
}

enum class ConnectionState {
    DISCONNECTED, CONNECTED, CONNECTING
}