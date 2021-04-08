package com.example.simulacion2pruebaegreso.model.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "detailPhones_table")
data class DetailPhones (@PrimaryKey val id: Int,
                         @SerializedName("name")
                         val name: String,
                         @SerializedName("price")
                         val price: Int,
                         @SerializedName("image")
                         val image: String,
                         @SerializedName("description")
                         val description: String,
                         @SerializedName("lastPrice")
                         val lastPrice: Int,
                         @SerializedName("credit")
                         val credit: Boolean)