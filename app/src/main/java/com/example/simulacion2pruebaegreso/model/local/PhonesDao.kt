package com.example.simulacion2pruebaegreso.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.simulacion2pruebaegreso.model.pojo.DetailPhones
import com.example.simulacion2pruebaegreso.model.pojo.Phones

@Dao
interface PhonesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPhones(list: List<Phones>)

    @Query("SELECT * FROM phones_table")
    fun getAllPhonesBD(): LiveData<List<Phones>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDetailPhones(list: List<DetailPhones>)

    @Query("SELECT * FROM detailPhones_table")
    fun getAllDetailPhonesBD(): LiveData<DetailPhones>

    @Query("SELECT * FROM detailPhones_table WHERE id= :id")
    fun getDetailPhonesById(id: Int): LiveData<DetailPhones>
}