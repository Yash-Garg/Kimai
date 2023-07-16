/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.ui.landing

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
import dev.yashgarg.kimai.R
import dev.yashgarg.kimai.di.CommonPreview
import dev.yashgarg.kimai.ui.theme.KimaiTheme

@Composable
fun LandingScreen(
  modifier: Modifier = Modifier,
  onGetStartedClick: () -> Unit = {},
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
      text = "Kimai",
      style = MaterialTheme.typography.headlineMedium,
      fontWeight = FontWeight.ExtraBold,
      letterSpacing = 1.sp,
    )
    Spacer(modifier = Modifier.weight(1f))
    FilledTonalButton(onClick = onGetStartedClick) {
      Text(text = stringResource(R.string.get_started))
    }
    Spacer(modifier = Modifier.size(24.dp))
  }
}

@CommonPreview
@Composable
fun Preview() {
  KimaiTheme { LandingScreen() }
}
