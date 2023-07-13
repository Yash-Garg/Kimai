/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.yashgarg.kimai.daos.ConfigDao
import dev.yashgarg.kimai.models.ClientConfig

@Database(version = 1, entities = [ClientConfig::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
  abstract fun configDao(): ConfigDao

  companion object {
    const val DB_NAME = "kimai.db"
  }
}
