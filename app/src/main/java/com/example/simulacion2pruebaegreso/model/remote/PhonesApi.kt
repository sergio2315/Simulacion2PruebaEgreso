package com.example.simulacion2pruebaegreso.model.remote

import com.example.simulacion2pruebaegreso.model.pojo.DetailPhones
import com.example.simulacion2pruebaegreso.model.pojo.Phones
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhonesApi {
    @GET("products")
    suspend fun fetchPhonesCoroutines(): Response<List<Phones>>

    /*@GET("details")
    suspend fun fetchDetailPhonesCoroutines(): Response<List<DetailPhones>>*/
    @GET("details/{id}")
    suspend fun fetchDetailPhonesCoroutines(@Path("id")id:Int): Response<DetailPhones>
}