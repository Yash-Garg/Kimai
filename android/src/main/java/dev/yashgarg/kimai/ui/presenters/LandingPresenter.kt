/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.presenters

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import com.deliveryhero.whetstone.app.ApplicationScope
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import dev.yashgarg.kimai.ui.screens.AuthScreen
import dev.yashgarg.kimai.ui.screens.LandingScreen
import dev.yashgarg.kimai.ui.screens.LandingScreen.LandingEvent
import dev.yashgarg.kimai.ui.screens.LandingScreen.LandingState

@SuppressLint("ComposableNaming")
@CircuitInject(LandingScreen::class, ApplicationScope::class)
@Composable
fun LandingPresenter(navigator: Navigator) = LandingState {
  when (it) {
    is LandingEvent.GetStarted -> navigator.goTo(AuthScreen)
  }
}
