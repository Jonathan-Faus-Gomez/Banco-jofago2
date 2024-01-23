package com.example.banco_jofago.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.banco_jofago.R
import com.example.banco_jofago.databinding.ActivityAtmFormBinding
import com.example.banco_jofago.entities.CajeroEntity

import com.example.banco_jototo.fragments.AddCajeroFragment
import com.example.banco_jototo.fragments.UpdateDeleteCajeroFragment

class AtmFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAtmFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtmFormBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val cajero = intent.getSerializableExtra("Cajero") as? CajeroEntity

        if (cajero == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.contenedorFragmentAtm, AddCajeroFragment()).commit()
            binding.titulo.text = "Crear Cajero"
        }else{
            val frgUpdate = UpdateDeleteCajeroFragment.newInstance(false, cajero)
            supportFragmentManager.beginTransaction().add(R.id.contenedorFragmentAtm, frgUpdate).commit()
            binding.titulo.text = "Modificar Cajero"
        }
    }

}