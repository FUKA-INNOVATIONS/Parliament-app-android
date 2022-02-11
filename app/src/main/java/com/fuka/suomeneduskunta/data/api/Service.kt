package com.fuka.suomeneduskunta.data.api

import com.fuka.suomeneduskunta.data.database.models.ParliamentMember
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


// Api URL to fetch
private const val BASE_URL = "https://avoindata.eduskunta.fi/"

// Build Moshi object
// Convert Json responose into Kotlin objects
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


// Build of retrofit
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    // Enable Retrofit to produce a coroutines-based API
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

// Return a list of Parliament Members
interface ParliamentApiService {
    @GET("api/v1/seating/")
    fun getAllMembers(): Deferred<List<ParliamentMember>>
}

// Implement the ParliamentApiService
object ParliamentApi {
    val service : ParliamentApiService by lazy { retrofit.create(ParliamentApiService::class.java) }
}
