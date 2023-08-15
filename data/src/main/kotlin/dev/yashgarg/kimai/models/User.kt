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
data class User(
  val initials: String,
  val id: Long,
  val alias: String,
  val title: String,
  val username: String,
  val accountNumber: String,
  val enabled: Boolean,
  val color: String
)
