/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.di

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers

@Preview(
  showSystemUi = true,
  device = "id:pixel_5",
  uiMode = Configuration.UI_MODE_NIGHT_YES,
  wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE,
)
annotation class CommonPreview
