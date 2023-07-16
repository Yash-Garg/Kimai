/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.yashgarg.kimai.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
  @Singleton
  @Provides
  fun provideRoomDb(@ApplicationContext context: Context) =
    Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME).build()

  @Singleton @Provides fun provideConfigDao(db: AppDatabase) = db.configDao()
}
