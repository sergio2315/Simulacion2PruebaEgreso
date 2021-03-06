package com.example.simulacion2pruebaegreso.model.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "phones_table")
data class Phones(@PrimaryKey val id: Int,
                  @SerializedName("name")
                  val name: String,
                  @SerializedName("price")
                  val price: Int,
                  @SerializedName("image")
                  val image: String)