/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.models

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Keep
@Serializable
@Entity(tableName = "instances")
data class InstanceConfig(
  @PrimaryKey @ColumnInfo("config_id") val id: Int,
  val url: String,
  val username: String,
  val apiToken: String,
  val isSecure: Boolean = false,
)
