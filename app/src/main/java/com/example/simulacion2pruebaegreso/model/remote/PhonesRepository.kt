package com.example.simulacion2pruebaegreso.model.remote

import android.util.Log
import com.example.simulacion2pruebaegreso.model.local.PhonesDao

class PhonesRepository(private val dao: PhonesDao) {
    val liveDataDB = dao.getAllPhonesBD()

    suspend fun getPhonesWithCourutines() {
        Log.d("REPOSITORY", "Utilizando corrutinas")
        try {
            val response = RetrofitClient.retrofitInstance().fetchPhonesCoroutines()
            when (response.isSuccessful) {
                true -> response.body()?.let {
                    //aca se inserta en la base de datos
                    dao.insertAllPhones(it)
                }
                false -> Log.d("ERROR", "${response.code()}: ${response.errorBody()} ")
            }
        } catch (t: Throwable) {
            Log.e("ERROR CORRUTINA", t.message.toString())
        }
    }
}