/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.home

import androidx.annotation.Keep
import dev.yashgarg.kimai.models.Activity
import dev.yashgarg.kimai.models.TimesheetActivity

@Keep
data class HomeState(
  val isLoading: Boolean = true,
  val error: String? = null,
  val activity: List<Activity>? = null,
  val projects: List<Activity>? = null,
  val timesheets: List<TimesheetActivity>? = null,
)
