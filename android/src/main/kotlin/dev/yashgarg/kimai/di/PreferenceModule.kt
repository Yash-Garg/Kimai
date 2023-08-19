/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.di

import android.content.SharedPreferences
import com.deliveryhero.whetstone.SingleIn
import com.deliveryhero.whetstone.app.ApplicationScope
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import dev.yashgarg.kimai.AppPreferences

@Module
@ContributesTo(ApplicationScope::class)
class PreferenceModule {

  @Provides
  @SingleIn(ApplicationScope::class)
  fun provideAppPreferences(sp: SharedPreferences): AppPreferences = AppPreferences(sp)
}
