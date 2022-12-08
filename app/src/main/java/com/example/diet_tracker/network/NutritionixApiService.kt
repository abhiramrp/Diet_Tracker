package com.example.diet_tracker.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

private const val BASE_URL = "https://api.nutritionix.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface NutritionixApiService {
    @Headers("appId: 18130c7f", "appKey: 8d090371e9b9a18dc005ccdb38f43482", "Content-Type: application/json")
    @POST("/v1_1/search")
    suspend fun getResults(@Query("q") search: String) : Call<FoodList>

}

object NutritionixApi {
    val retrofitService: NutritionixApiService by lazy { retrofit.create(NutritionixApiService::class.java) }
}