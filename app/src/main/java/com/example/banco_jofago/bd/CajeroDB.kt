package com.example.banco_jofago.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.banco_jofago.dao.CajeroDAO
import com.example.banco_jofago.entities.CajeroEntity

@Database(entities = [CajeroEntity::class], version = 1)
abstract class CajeroDB: RoomDatabase() {

    abstract fun cajeroDao(): CajeroDAO
}
