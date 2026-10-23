package org.github.amanbutnot.prism.console.data

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import org.github.amanbutnot.prism.core.constants.DISCOVERY_ACTION

class PrismServiceDiscovery(private val packageManager: PackageManager) {
    fun discover(): List<PrismServiceInfo> {
        val res = packageManager.queryIntentServices(
            Intent(DISCOVERY_ACTION), PackageManager.MATCH_DEFAULT_ONLY
        )
        return res.map {
            PrismServiceInfo(it.serviceInfo.packageName, it.serviceInfo.name)
        }
    }
}
