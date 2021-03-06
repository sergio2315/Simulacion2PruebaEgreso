package com.example.simulacion2pruebaegreso.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.simulacion2pruebaegreso.model.pojo.DetailPhones
import com.example.simulacion2pruebaegreso.model.pojo.Phones

@Database(entities = [Phones::class],version = 1)
abstract class PhonesDataBase: RoomDatabase(){
    abstract fun getPhonesDao(): PhonesDao

    companion object {
        @Volatile
        private var INSTANCE: PhonesDataBase? = null

        fun getDataBase(context: Context): PhonesDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhonesDataBase::class.java, "phonesDB"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}