package org.d3if4079.hitungjumlahsatuan.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if4079.hitungjumlahsatuan.ui.DataDiri
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DataDiriApiService {
    @GET("posts")
    suspend fun getDataDiri(): List<DataDiri>
}
object DataDiriApi  {
    val service: DataDiriApiService by lazy {
        retrofit.create(DataDiriApiService::class.java)
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }