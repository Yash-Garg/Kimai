/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deliveryhero.whetstone.app.ApplicationScope
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Screen
import dev.yashgarg.kimai.R
import dev.yashgarg.kimai.ui.screens.LandingScreen.LandingEvent
import dev.yashgarg.kimai.ui.screens.LandingScreen.LandingState
import kotlinx.parcelize.Parcelize

@Parcelize
object LandingScreen : Screen {
  data class LandingState(
    val eventSink: (LandingEvent) -> Unit,
  ) : CircuitUiState

  sealed interface LandingEvent : CircuitUiEvent {
    data object GetStarted : LandingEvent
  }
}

@CircuitInject(LandingScreen::class, ApplicationScope::class)
@Composable
fun LandingUi(
  state: LandingState,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = Modifier.fillMaxSize().then(modifier),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Spacer(modifier = Modifier.weight(1f))
    Image(
      painter = painterResource(id = R.drawable.kimai_logo),
      contentDescription = "Kimai Logo",
      modifier = Modifier.size(80.dp),
      contentScale = ContentScale.Crop,
    )
    Spacer(modifier = Modifier.size(8.dp))
    Text(
      text = stringResource(R.string.app_name),
      style = MaterialTheme.typography.headlineMedium,
      fontWeight = FontWeight.ExtraBold,
      letterSpacing = 1.sp,
    )
    Spacer(modifier = Modifier.weight(1f))
    FilledTonalButton(onClick = { state.eventSink(LandingEvent.GetStarted) }) {
      Text(text = stringResource(R.string.get_started))
    }
    Spacer(modifier = Modifier.size(24.dp))
  }
}
