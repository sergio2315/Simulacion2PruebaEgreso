package com.example.simulacion2pruebaegreso.model.remote

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.simulacion2pruebaegreso.model.local.PhonesDao
import com.example.simulacion2pruebaegreso.model.pojo.DetailPhones

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
    fun converter(id: Int, name: String, price: Int, image: String, description: String,
                  lastPrice: Int, credit: Boolean): List<DetailPhones> {
        val listDetailPhones: MutableList<DetailPhones> = mutableListOf<DetailPhones>()
        listDetailPhones.add( DetailPhones
                (id = id
                , name = name
                ,price = price
                ,image = image
                ,description = description
                ,lastPrice = lastPrice
                ,credit = credit
        )
        )
        return listDetailPhones
    }
    suspend fun getDetailPhonesWithCourutines(id: Int) {
        Log.d("REPOSITORY", "Utilizando corrutinas")
        try {
            val response = RetrofitClient.retrofitInstance().fetchDetailPhonesCoroutines(id)
            when (response.isSuccessful) {
                true -> response.body()?.let {
                    //aca se inserta en la base de datos
                    dao.insertAllDetailPhones(converter(id, name = it.name
                            ,price = it.price
                            ,image = it.image
                            ,description = it.description
                            ,lastPrice = it.lastPrice
                            ,credit = it.credit))
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