package com.example.banco_jofago.bd


import android.app.Application
import androidx.room.Room

class CajeroApplication: Application() {
    companion object{
        lateinit var db: CajeroDB
    }
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, CajeroDB::class.java, "CajeroDB").build()
    }
}