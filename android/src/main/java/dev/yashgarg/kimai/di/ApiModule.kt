/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.di

import com.deliveryhero.whetstone.SingleIn
import com.deliveryhero.whetstone.app.ApplicationScope
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dev.yashgarg.kimai.api.KimaiApi
import dev.yashgarg.kimai.api.KimaiRepository
import dev.yashgarg.kimai.api.KimaiRepositoryImpl
import dev.yashgarg.kimai.util.HostSelectionInterceptor
import dev.zacsweers.moshix.reflect.MetadataKotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@ContributesTo(ApplicationScope::class)
@Module
object ApiModule {
  @Provides
  @SingleIn(ApplicationScope::class)
  fun provideHostSelectionInterceptor(): HostSelectionInterceptor = HostSelectionInterceptor()

  @Provides
  @SingleIn(ApplicationScope::class)
  fun provideOkHttpClient(hostInterceptor: HostSelectionInterceptor): OkHttpClient {
    val logging =
      HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
        redactHeader("Authorization")
      }

    return OkHttpClient.Builder().addInterceptor(logging).addInterceptor(hostInterceptor).build()
  }

  @Provides
  @SingleIn(ApplicationScope::class)
  fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit =
    Retrofit.Builder()
      .baseUrl(KimaiApi.BASE_URL)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .client(client)
      .build()

  @Provides
  @SingleIn(ApplicationScope::class)
  fun provideMoshi(): Moshi = Moshi.Builder().add(MetadataKotlinJsonAdapterFactory()).build()

  @Provides
  @SingleIn(ApplicationScope::class)
  fun provideBaseApi(retrofit: Retrofit): KimaiApi = retrofit.create(KimaiApi::class.java)

  @Provides
  @SingleIn(ApplicationScope::class)
  fun provideRepository(api: KimaiApi): KimaiRepository = KimaiRepositoryImpl(api)
}
