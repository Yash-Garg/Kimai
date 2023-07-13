/*
 * Copyright © 2023 Yash Garg.
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package dev.yashgarg.kimai.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yashgarg.kimai.api.KimaiApi
import dev.zacsweers.moshix.reflect.MetadataKotlinJsonAdapterFactory
import javax.inject.Singleton
import kotlinx.coroutines.MainScope
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
  @Singleton
  @Provides
  fun provideOkHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder().addInterceptor(logging).build()
  }

  @Singleton
  @Provides
  fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit =
    Retrofit.Builder()
      .baseUrl(KimaiApi.BASE_URL)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .client(client)
      .build()

  @Singleton
  @Provides
  fun provideMoshi(): Moshi = Moshi.Builder().add(MetadataKotlinJsonAdapterFactory()).build()

  @Singleton
  @Provides
  fun provideBaseApi(retrofit: Retrofit): KimaiApi = retrofit.create(KimaiApi::class.java)

  @ApplicationScope @Provides fun provideCoroutineScope() = MainScope()
}
