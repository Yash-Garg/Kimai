/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.activity

import androidx.annotation.Keep
import dev.yashgarg.kimai.models.Activity
import dev.yashgarg.kimai.models.Customer
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

@Keep
data class AddTimeViewState(
  val selectedDate: Long? = null,
  val selectedStartTime: Long? = null,
  val selectedDuration: String? = null,
  val durations: Map<String, Duration> =
    mapOf(
      "00:15" to 15.minutes,
      "00:30" to 30.minutes,
      "00:45" to 45.minutes,
      "01:00" to 60.minutes,
      "01:15" to 75.minutes,
      "01:30" to 90.minutes,
      "01:45" to 105.minutes,
      "02:00" to 120.minutes,
      "02:15" to 135.minutes,
      "02:30" to 150.minutes,
      "02:45" to 165.minutes,
      "03:00" to 180.minutes,
      "03:15" to 195.minutes,
      "03:30" to 210.minutes,
      "03:45" to 225.minutes,
      "04:00" to 240.minutes,
      "04:15" to 255.minutes,
      "04:30" to 270.minutes,
      "04:45" to 285.minutes,
      "05:00" to 300.minutes,
      "05:15" to 315.minutes,
      "05:30" to 330.minutes,
      "05:45" to 345.minutes,
      "06:00" to 360.minutes,
      "06:15" to 375.minutes,
      "06:30" to 390.minutes,
      "06:45" to 405.minutes,
      "07:00" to 420.minutes,
      "07:15" to 435.minutes,
      "07:30" to 450.minutes,
      "07:45" to 465.minutes,
      "08:00" to 480.minutes,
      "08:15" to 495.minutes,
      "08:30" to 510.minutes,
      "08:45" to 525.minutes,
      "09:00" to 540.minutes,
      "09:15" to 555.minutes,
      "09:30" to 570.minutes,
      "09:45" to 585.minutes,
      "10:00" to 600.minutes,
    ),
  val selectedCustomer: String? = null,
  val selectedProject: String? = null,
  val selectedActivity: String? = null,
  val description: String? = null,
  val activities: List<Activity> = emptyList(),
  val customers: List<Customer> = emptyList(),
  val projects: List<Activity> = emptyList(),
  val isLoading: Boolean = true,
  val error: String? = null,
)
