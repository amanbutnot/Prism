package org.github.amanbutnot.prism.library

import android.app.Service
import android.content.Intent
import android.os.IBinder
import org.github.amanbutnot.prism.core.ipc.IPrismService

class PrismService : Service() {
    private val binder = object : IPrismService.Stub() {
        override fun getPrismVersion(): String {
            return "1.0"
        }
    }

    override fun onBind(p0: Intent?): IBinder {
        return binder
    }

}