package org.dean.mykotlinapplication.api

import okhttp3.OkHttpClient
import org.dean.mykotlinapplication.data.Teaser
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by dean on 2017/11/27.
 */
interface DribbleApiService {

    @GET("v1/shots")
    fun search(@Query("sort") query: String = "recent",
               @Query("page") page: Int = 1,
               @Query("per_page") perPage: Int = 30): io.reactivex.Observable<List<Teaser>>

    /**
     * Companion object for the factory
     */
    companion object Factory {
        fun create(): DribbleApiService {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor {
                it.proceed(it.request().newBuilder()
                        .header("Authorization", "Bearer a62b88ea291c0d0e5b9295fdb8930936f945027bb84ff747ef6b89f8a9cd4da1").build())
            }
            val retrofit = Retrofit.Builder()
                    .client(httpClient.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.dribbble.com/")
                    .build()

            return retrofit.create(DribbleApiService::class.java)
        }
    }
}