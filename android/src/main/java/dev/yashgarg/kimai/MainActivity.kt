/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.view.WindowCompat
import com.deliveryhero.whetstone.Whetstone
import com.deliveryhero.whetstone.activity.ContributesActivityInjector
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import dev.yashgarg.kiami.ui.theme.KimaiTheme
import javax.inject.Inject

@ContributesActivityInjector
class MainActivity : ComponentActivity() {

  @Inject lateinit var circuit: Circuit

  override fun onCreate(savedInstanceState: Bundle?) {
    Whetstone.inject(this)
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)

    setContent { KimaiTheme { CompositionLocalProvider { CircuitCompositionLocals(circuit) {} } } }
  }
}
