/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.models

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class TimesheetActivity(
  val activity: Int,
  val project: Int,
  val tags: List<String>,
  val id: Int,
  val begin: String,
  val end: String,
  val duration: Int,
  val description: String? = null,
  val rate: Double,
  val internalRate: Double,
  val exported: Boolean,
  val billable: Boolean,
  val metaFields: List<MetaField>,
)
