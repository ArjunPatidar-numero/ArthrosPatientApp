package  com.numeroeins.arthros.patient.servermanager

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


object APIClient {
    private var retrofit: Retrofit? = null
    private const val REQUEST_TIMEOUT = 60
    private var okHttpClient: OkHttpClient? = null
    fun getClient(context: Context): Retrofit? {
        if (okHttpClient == null) initOkHttp(context)
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(UrlManager.MAIN_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit
    }

    private fun initOkHttp(context: Context) {
        val httpClient = OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json")

                // Adding Authorization token (API Key)
                // Requests will be denied without API key
                /* if (!TextUtils.isEmpty(PrefUtils.getApiKey(context))) {
                    requestBuilder.addHeader("Authorization", PrefUtils.getApiKey(context));
                }*/
                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        })
        okHttpClient = httpClient.build()
    }

    val gsonAsConvert: Gson
        get() = GsonBuilder()
                .setLenient()
                .registerTypeAdapter(Int::class.java, IntDeserializer())
                .registerTypeAdapter(Boolean::class.java, BooleanDeserializer())
                .registerTypeAdapter(Double::class.java, DoubleDeserializer())
                .create()
}

