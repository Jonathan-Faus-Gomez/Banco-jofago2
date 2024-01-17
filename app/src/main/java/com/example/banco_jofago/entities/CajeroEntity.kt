package com.example.banco_jofago.entities
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cajero")
class CajeroEntity (@PrimaryKey(autoGenerate = true) val id: Int,
                    val direccion: String,
                    val longitud: Double,
                    val latitud: Double,
                    val zoom: String)
