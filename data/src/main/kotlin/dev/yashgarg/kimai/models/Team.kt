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
data class Team(
  val id: Long,
  val name: String,
  val members: List<Member>,
  val customers: List<ActivityInner>,
  val projects: List<ActivityInner>,
  val activities: List<ActivityInner>,
  val color: String
)
