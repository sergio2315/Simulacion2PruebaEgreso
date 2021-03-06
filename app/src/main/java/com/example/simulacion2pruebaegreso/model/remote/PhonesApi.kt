package com.example.simulacion2pruebaegreso.model.remote

import com.example.simulacion2pruebaegreso.model.pojo.Phones
import retrofit2.Response
import retrofit2.http.GET

interface PhonesApi {
    @GET("products")
    suspend fun fetchPhonesCoroutines(): Response<List<Phones>>

    @GET("details")
    suspend fun fetchDetailPhonesCoroutines(): Response<List<Phones>>
}