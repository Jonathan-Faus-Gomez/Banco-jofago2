package com.example.banco_jofago.entities
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Cajero (@PrimaryKey(autoGenerate = true) val id: Int,
              val direccion: String,
              val longitud: Double,
              val latitud: Double)
