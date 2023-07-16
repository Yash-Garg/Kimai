/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.util

import java.io.IOException
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.Request
import okhttp3.Response

class HostSelectionInterceptor : Interceptor {
  @Volatile private var host: String? = null
  @Volatile private var headerMap: Map<String, String>? = null

  fun setHost(host: String?) {
    this.host = host?.toHttpUrlOrNull()?.host
  }

  fun setHeaderMap(headerMap: Map<String, String>?) {
    this.headerMap = headerMap
  }

  @Throws(IOException::class)
  override fun intercept(chain: Chain): Response {
    var request: Request = chain.request()

    val host = host
    val headers = headerMap

    val builder = request.newBuilder()
    if (host != null) {
      val newUrl: HttpUrl = request.url.newBuilder().host(host).build()
      builder.url(newUrl)
    }

    if (headerMap != null) {
      for ((key, value) in headers!!) {
        builder.addHeader(key, value)
      }
    }

    request = builder.build()
    return chain.proceed(request)
  }
}
