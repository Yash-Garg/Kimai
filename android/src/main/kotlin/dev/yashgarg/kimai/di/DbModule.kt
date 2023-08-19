/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.di

import android.content.Context
import androidx.room.Room
import com.deliveryhero.whetstone.SingleIn
import com.deliveryhero.whetstone.app.ApplicationScope
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import dev.yashgarg.kimai.AppDatabase
import dev.yashgarg.kimai.daos.ConfigDao

@Module
@ContributesTo(ApplicationScope::class)
class DbModule {
  @Provides
  @SingleIn(ApplicationScope::class)
  fun provideRoomDb(context: Context): AppDatabase =
    Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME).build()

  @Provides
  @SingleIn(ApplicationScope::class)
  fun provideConfigDao(db: AppDatabase): ConfigDao = db.configDao()
}
