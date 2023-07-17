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

@Keep
data class AddTimeViewState(
  val selectedDate: String? = null,
  val selectedStartTime: String? = null,
  val selectedDuration: String? = null,
  val durations: List<String> =
    listOf(
      "00:00",
      "00:15",
      "00:30",
      "00:45",
      "01:00",
      "01:15",
      "01:30",
      "01:45",
      "02:00",
      "02:15",
      "02:30",
      "02:45",
      "03:00",
      "03:15",
      "03:30",
      "03:45",
      "04:00",
      "04:15",
      "04:30",
      "04:45",
      "05:00",
      "05:15",
      "05:30",
      "05:45",
      "06:00",
      "06:15",
      "06:30",
      "06:45",
      "07:00",
      "07:15",
      "07:30",
      "07:45",
      "08:00",
      "08:15",
      "08:30",
      "08:45",
      "09:00",
      "09:15",
      "09:30",
      "09:45",
      "10:00"
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
