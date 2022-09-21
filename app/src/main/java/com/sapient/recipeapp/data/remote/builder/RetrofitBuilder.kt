package com.sapient.recipeapp.data.remote.builder

import android.content.Context
import androidx.viewbinding.BuildConfig
import com.google.gson.GsonBuilder
import com.sapient.recipeapp.R
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val timeoutRead = 30   //In seconds
private const val timeoutConnect = 30   //In seconds

@Singleton
class RetrofitBuilder @Inject constructor(@ApplicationContext private val context: Context) {
    private var baseUrl: String = context.getString(R.string.base_url)
    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private var interceptors = mutableListOf<Interceptor>()

    /**
     * add custom interceptor for ok http client
     * @param interceptor is a interceptor for ok http client
     */
    fun addInterceptors(vararg interceptor: Interceptor): RetrofitBuilder {
        interceptors.addAll(interceptor)
        return this
    }

    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
            }
            return loggingInterceptor
        }

    fun build(): Retrofit {
        okHttpBuilder.addInterceptor(logger)
        okHttpBuilder.connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
        val client = okHttpBuilder.build()
        return Retrofit.Builder()
            .baseUrl(baseUrl).client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }
}