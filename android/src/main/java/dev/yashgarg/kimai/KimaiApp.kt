/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai

import android.app.Application
import com.deliveryhero.whetstone.app.ApplicationComponentOwner
import com.deliveryhero.whetstone.app.ContributesAppInjector

@ContributesAppInjector(generateAppComponent = true)
class KimaiApp : Application(), ApplicationComponentOwner {
  override val applicationComponent by lazy { GeneratedApplicationComponent.create(this) }
}
