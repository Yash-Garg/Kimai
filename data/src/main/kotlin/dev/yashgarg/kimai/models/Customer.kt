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
data class Customer(
  val id: Int,
  val name: String,
  val number: String,
  val comment: String? = null,
  val visible: Boolean,
  val billable: Boolean,
  val metaFields: List<MetaField>,
  val teams: List<Team>,
  val color: String? = null
)
