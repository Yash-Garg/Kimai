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
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.yashgarg.kimai.AppDatabase

@ContributesTo(ApplicationScope::class)
@Module
class DbModule {
  @Provides
  @SingleIn(ApplicationScope::class)
  fun provideRoomDb(@ApplicationContext context: Context) =
    Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME).build()

  @Provides
  @SingleIn(ApplicationScope::class)
  fun provideConfigDao(db: AppDatabase) = db.configDao()
}
