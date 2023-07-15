/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import dev.yashgarg.kimai.models.InstanceConfig

@Dao
interface ConfigDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE) fun addInstance(config: InstanceConfig)
}
