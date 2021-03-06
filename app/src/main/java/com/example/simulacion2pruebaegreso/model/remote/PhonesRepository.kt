package com.example.simulacion2pruebaegreso.model.remote

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.simulacion2pruebaegreso.model.local.PhonesDao
import com.example.simulacion2pruebaegreso.model.pojo.DetailPhones

class PhonesRepository(private val dao: PhonesDao) {
    val liveDataDB = dao.getAllPhonesBD()
    val liveDataDetailsDB = dao.getAllDetailPhonesBD()

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
    suspend fun getDetailPhonesWithCourutines() {
        Log.d("REPOSITORY", "Utilizando corrutinas")
        try {
            val response = RetrofitClient.retrofitInstance().fetchDetailPhonesCoroutines()
            when (response.isSuccessful) {
                true -> response.body()?.let {
                    //aca se inserta en la base de datos
                    dao.insertAllDetailPhones(it)
                }
                false -> Log.d("ERROR", "${response.code()}: ${response.errorBody()} ")
            }
        } catch (t: Throwable) {
            Log.e("ERROR CORRUTINA", t.message.toString())
        }
    }
    fun getDetailPhonesByID(id: Int) : LiveData<DetailPhones> {
        return dao.getDetailPhonesById(id)
    }
}