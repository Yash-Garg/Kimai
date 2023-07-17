/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.activity

sealed class AddTimeViewEvent {
  data class DateSelected(val date: String) : AddTimeViewEvent()

  data class StartTimeSelected(val time: String) : AddTimeViewEvent()

  data class DurationSelected(val duration: String) : AddTimeViewEvent()

  data class CustomerSelected(val customer: String) : AddTimeViewEvent()

  data class ProjectSelected(val project: String) : AddTimeViewEvent()

  data class ActivitySelected(val activity: String) : AddTimeViewEvent()

  data class DescriptionSelected(val description: String = "") : AddTimeViewEvent()

  object Save : AddTimeViewEvent()
}
