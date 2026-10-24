package org.github.amanbutnot.prism.console.data

import android.content.ComponentName
import android.content.Context
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder

class PrismServiceConnector(private val info: PrismServiceInfo, private val context: Context) {
    private val compName = ComponentName(info.packageName, info.serviceClassName)
    private var isBound = false;
    private val compIntent = Intent().setComponent(compName)
    private var binder: IBinder? = null
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(
            p0: ComponentName?,
            p1: IBinder?
        ) {
            binder = p1
            println("Service is connected and binder for testing is $p1")
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            binder = null
            println("service is diconnected")
        }
    }

    fun connect() {
        val res = context.bindService(compIntent, serviceConnection, BIND_AUTO_CREATE)
        isBound = res

    }

    fun disconnect() {
        if (isBound) {
            context.unbindService(serviceConnection)
            binder = null
            isBound = false
        }

    }
}