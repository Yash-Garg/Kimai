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
  val activityState: ActivityState = ActivityState.Loading,
  val projectsState: ProjectsState = ProjectsState.Loading,
  val timesheetState: TimesheetState = TimesheetState.Loading,
)

sealed class ActivityState {
  object Loading : ActivityState()

  data class Success(val activities: List<Activity>) : ActivityState()

  data class Error(val message: String) : ActivityState()
}

sealed class ProjectsState {
  object Loading : ProjectsState()

  data class Success(val projects: List<Activity>) : ProjectsState()

  data class Error(val message: String) : ProjectsState()
}

sealed class TimesheetState {
  object Loading : TimesheetState()

  data class Success(val timesheets: List<TimesheetActivity>) : TimesheetState()

  data class Error(val message: String) : TimesheetState()
}
