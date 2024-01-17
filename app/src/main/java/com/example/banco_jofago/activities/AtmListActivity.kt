package com.example.banco_jofago.activities

import CajeroAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_jofago.R
import com.example.banco_jofago.entities.CajeroEntity

class AtmListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var cajeroAdapter: CajeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atm_list)

        // cajeros de la base de datos
        val cajerosList: List<CajeroEntity> = ArrayList <CajeroEntity>() //cambiar esto
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        cajeroAdapter = CajeroAdapter(cajerosList)
        recyclerView.adapter = cajeroAdapter
    }
}