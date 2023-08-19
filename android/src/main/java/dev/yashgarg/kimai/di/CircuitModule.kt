/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.di

import com.deliveryhero.whetstone.app.ApplicationScope
import com.slack.circuit.foundation.Circuit
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import dev.yashgarg.kimai.ui.presenters.AuthPresenterFactory
import dev.yashgarg.kimai.ui.presenters.LandingPresenterFactory
import dev.yashgarg.kimai.ui.screens.AuthUiFactory
import dev.yashgarg.kimai.ui.screens.LandingUiFactory

@Module
@ContributesTo(ApplicationScope::class)
interface CircuitModule {
  companion object {
    @Provides
    fun provideCircuit(): Circuit {
      val presenterFactories =
        listOf(
          LandingPresenterFactory(),
          AuthPresenterFactory(),
        )

      val uiFactories =
        listOf(
          LandingUiFactory(),
          AuthUiFactory(),
        )

      return Circuit.Builder()
        .addPresenterFactories(presenterFactories)
        .addUiFactories(uiFactories)
        .build()
    }
  }
}
