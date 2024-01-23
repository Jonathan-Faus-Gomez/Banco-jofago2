package com.example.banco_jofago.entities
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Cajero")
data class CajeroEntity (@PrimaryKey(autoGenerate = true) val id: Int = 0,
                    val direccion: String,
                    val longitud: Double,
                    val latitud: Double,
                    val zoom: String =""): Serializable
