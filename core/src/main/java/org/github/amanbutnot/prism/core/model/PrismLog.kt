package org.github.amanbutnot.prism.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PrismLog(
    val id: Long,
    val timestamp: Long,
    val level: String,
    val tag: String,
    val message: String,
    val thread: String,
    val exception: String?,
    val stackTrace: String?
) : Parcelable