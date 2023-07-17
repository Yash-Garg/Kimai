/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.models

import androidx.annotation.Keep

@Keep
data class TimesheetBody(
  val begin: String,
  val end: String? = null,
  val project: Int = 0,
  val activity: Int = 0,
  val description: String = "",
)
