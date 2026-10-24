package org.github.amanbutnot.prism.library

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class PrismService : Service() {
    private val binder = Binder()
    override fun onBind(p0: Intent?): IBinder {
        return binder
    }
}