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

@Keep
@Entity(tableName = "configs")
data class ClientConfig(
  @PrimaryKey @ColumnInfo("config_id") val configId: Int,
  val serverUrl: String,
  val username: String,
  val apiToken: String,
)
