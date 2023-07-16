/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai

import java.io.IOException
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.Request
import okhttp3.Response

class HostSelectionInterceptor : Interceptor {
  @Volatile private var host: String? = null

  fun setHost(host: String?) {
    this.host = host?.toHttpUrlOrNull()?.host
  }

  @Throws(IOException::class)
  override fun intercept(chain: Chain): Response {
    var request: Request = chain.request()
    val host = host

    if (host != null) {
      val newUrl: HttpUrl = request.url.newBuilder().host(host).build()
      request = request.newBuilder().url(newUrl).build()
    }

    return chain.proceed(request)
  }
}
