package com.example.banco_jofago.activities

import CajeroAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_jofago.adapters.OnClickListenerCajero
import com.example.banco_jofago.bd.CajeroApplication
import com.example.banco_jofago.databinding.ActivityAtmListBinding
import com.example.banco_jofago.entities.CajeroEntity
import java.util.concurrent.LinkedBlockingQueue

class AtmListActivity : AppCompatActivity(),OnClickListenerCajero {

    private lateinit var cajeroAdapter: CajeroAdapter
    private lateinit var binding: ActivityAtmListBinding
    private lateinit var linearLayout: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtmListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()




    }
    private fun setupRecyclerView(){
        cajeroAdapter = CajeroAdapter(mutableListOf(),this)
        linearLayout = LinearLayoutManager(this)
        getCajeros()
        binding.recyclerView.apply {
            layoutManager = linearLayout
            adapter = cajeroAdapter
        }

    }
    private fun getCajeros(){
        val queue = LinkedBlockingQueue<MutableList<CajeroEntity>>()
        Thread{
            val cajeros = CajeroApplication.db.cajeroDao().getAllCajeros()
            queue.add(cajeros)
        }.start()
        cajeroAdapter.setStores(queue.take())

    }
    override fun onClick(cajero: CajeroEntity) {
        val intent = Intent(this, AtmFormActivity::class.java)
        intent.putExtra("Cajero", cajero)
        startActivity(intent)
    }
}