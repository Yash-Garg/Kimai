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
  val members: List<Member>? = null,
  val customers: List<ActivityInner>? = null,
  val projects: List<ActivityInner>? = null,
  val activities: List<ActivityInner>? = null,
  val color: String
)
