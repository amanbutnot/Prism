package org.github.amanbutnot.prism.library

import android.app.Service
import android.content.Intent
import android.os.IBinder
import org.github.amanbutnot.prism.core.ipc.IPrismService
import org.github.amanbutnot.prism.core.model.PrismLog

class PrismService : Service() {
    private val binder = object : IPrismService.Stub() {
        override fun getPrismVersion(): String {
            return "1.0"
        }

        override fun getLogs(): List<PrismLog?> {
            return listOf(
                PrismLog(
                    id = 1,
                    timestamp = System.currentTimeMillis(),
                    level = "level",
                    tag = "tag",
                    message = "this is a message",
                    thread = "main thread",
                    exception = "exception",
                    stackTrace = "stack trace"
                ),
            )
        }


    }

    override fun onBind(p0: Intent?): IBinder {
        return binder
    }

}