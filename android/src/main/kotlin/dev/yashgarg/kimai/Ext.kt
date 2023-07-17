/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun ColorScheme.surfaceColorAtNavigationBarElevation(): Color {
  val elevation = LocalAbsoluteTonalElevation.current + NavigationBarDefaults.Elevation
  return surfaceColorAtElevation(elevation)
}

private object NumberFormat {
  fun secondsToTime(seconds: Long): String {
    var duration = seconds
    val days: Long = TimeUnit.SECONDS.toDays(duration)
    duration -= TimeUnit.DAYS.toSeconds(days)
    val hours: Long = TimeUnit.SECONDS.toHours(duration)
    duration -= TimeUnit.HOURS.toSeconds(hours)
    val minutes: Long = TimeUnit.SECONDS.toMinutes(duration)
    duration -= TimeUnit.MINUTES.toSeconds(minutes)
    val secs: Long = TimeUnit.SECONDS.toSeconds(duration)

    val timeStr = StringBuilder()
    if (days != 0L) {
      timeStr.append("$days days")
    }
    if (hours != 0L) {
      timeStr.append(" $hours hours")
    }
    if (minutes != 0L) {
      timeStr.append(" $minutes minutes")
    }
    if (secs != 0L) {
      timeStr.append(" $secs seconds")
    }

    return timeStr.toString().trim()
  }

  fun millisToDate(millis: Long, zoneId: ZoneId?): String {
    val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy")
    val instant = Instant.ofEpochMilli(millis)
    val date = LocalDateTime.ofInstant(instant, zoneId ?: ZoneId.systemDefault())
    return formatter.format(date).trim()
  }

  fun millisToOnlyDate(millis: Long, zoneId: ZoneId?): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val instant = Instant.ofEpochMilli(millis)
    val date = LocalDateTime.ofInstant(instant, zoneId ?: ZoneId.systemDefault())
    return formatter.format(date).trim()
  }

  fun millisToTime(millis: Long, zoneId: ZoneId?): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    val instant = Instant.ofEpochMilli(millis)
    val date = LocalDateTime.ofInstant(instant, zoneId ?: ZoneId.systemDefault())
    return formatter.format(date).trim()
  }
}

fun Int.toTime(): String = NumberFormat.secondsToTime(this.toLong())

fun String.toDateTime(): String {
  val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")
  val offsetDateTime = OffsetDateTime.parse(this, formatter)

  val outputFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm a", Locale.ENGLISH)

  return offsetDateTime.toLocalDateTime().format(outputFormatter)
}

fun Long.toOnlyDate(zoneId: ZoneId? = null): String = NumberFormat.millisToOnlyDate(this, zoneId)

fun Long.toTime(zoneId: ZoneId? = null): String = NumberFormat.millisToTime(this, zoneId)

fun Long.toDate(zoneId: ZoneId? = null): String = NumberFormat.millisToDate(this, zoneId)

@Composable
fun <T> Flow<T>.collectAsEffect(
  context: CoroutineContext = EmptyCoroutineContext,
  block: (T) -> Unit
) {
  LaunchedEffect(key1 = Unit) { onEach(block).flowOn(context).launchIn(this) }
}
