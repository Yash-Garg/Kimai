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
data class Activity(
  val parentTitle: String,
  val project: Long,
  val id: Long,
  val name: String,
  val comment: String,
  val visible: Boolean,
  val billable: Boolean,
  val metaFields: List<MetaField>,
  val teams: List<Team>,
  val color: String
)
