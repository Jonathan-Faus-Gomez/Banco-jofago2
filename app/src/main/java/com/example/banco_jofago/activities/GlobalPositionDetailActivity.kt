package com.example.banco_jofago.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_jofago.R
import com.example.banco_jofago.adapters.MovimientoAdapter
import com.example.banco_jofago.bd.MiBancoOperacional
import com.example.banco_jofago.databinding.ActivityGlobalPositionBinding
import com.example.banco_jofago.databinding.ActivityGlobalPositionDetailBinding
import com.example.banco_jofago.fragments.CuentasFragment
import com.example.banco_jofago.fragments.CuentasMovimientosFragment
import com.example.banco_jofago.pojo.Cliente
import com.example.banco_jofago.pojo.Cuenta
import com.example.banco_jofago.pojo.Movimiento

class GlobalPositionDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGlobalPositionDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityGlobalPositionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val cuenta = intent.getSerializableExtra("cuenta") as Cuenta
        val fragmentCuentasMovements: CuentasMovimientosFragment = CuentasMovimientosFragment.newInstance(cuenta)


        supportFragmentManager.beginTransaction().add(R.id.frgMovements,fragmentCuentasMovements).commit()

    }
    //onMovimientoSeleccionado ->movil
}