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
data class ActivityInner(
  val project: Long? = null,
  val id: Long,
  val name: String,
  val comment: String,
  val visible: Boolean,
  val billable: Boolean,
  val color: String,
  val number: String? = null,
  val customer: Long? = null,
  val globalActivities: Boolean? = null
)
