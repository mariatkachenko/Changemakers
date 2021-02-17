package com.example.changemakers.network

import retrofit2.Retrofit
import retrofit2.http.GET
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Query

enum class CmApiFilter(val value: String) {
    SHOW_FOUND("Foundation"),
    SHOW_BUY("buy"),
    SHOW_ALL("all") }

private const val BASE_URL = "https://makeup-api.herokuapp.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CmApiService {
    @GET("api/v1/products.json?brand=nyx")
    suspend fun getProperties(@Query("filter") type: String): List<CmProperty>
}

object CmApi {
    val retrofitService : CmApiService by lazy {
        retrofit.create(CmApiService::class.java) }
}