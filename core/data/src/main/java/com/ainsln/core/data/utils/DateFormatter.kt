package com.ainsln.core.data.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface DateFormatter {
    fun parseDate(date: String): Date
    fun getRemainingDays(date: String?): Int
}

internal class BaseDateFormatter @Inject constructor() : DateFormatter {
    private val simpleFormatter = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())

    override fun parseDate(date: String): Date {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
                val zonedDateTime = OffsetDateTime.parse(date, formatter)
                    .atZoneSameInstant(ZoneId.systemDefault())
                Date.from(zonedDateTime.toInstant())
            } else {
                simpleFormatter.timeZone = TimeZone.getTimeZone(TIME_ZONE)
                return simpleFormatter.parse(date) ?: Date()
            }
        } catch (e: Throwable) {
            Date()
        }
    }

    override fun getRemainingDays(date: String?): Int {
        if (date == null) return 0
        return TimeUnit.MILLISECONDS.toDays(
            parseDate(date).time - Date().time
        ).toInt() + 1
    }

    companion object {
        private const val DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX"
        private const val TIME_ZONE = "UTC"
    }
}
