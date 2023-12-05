// MovementsActivity.kt
package com.example.banco_jofago.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_jofago.R
import com.example.banco_jofago.adapters.MovimientoAdapter
import com.example.banco_jofago.bd.MiBancoOperacional
import com.example.banco_jofago.databinding.ActivityMovementsBinding
import com.example.banco_jofago.pojo.Cliente
import com.example.banco_jofago.pojo.Cuenta
import com.example.banco_jofago.pojo.Movimiento

class MovementsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovementsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var spinner: Spinner
    private var selectedCuenta: Cuenta? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovementsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        spinner = findViewById(R.id.spinner)

        // Obtén la lista de cuentas del usuario

        val cliente = intent.getSerializableExtra("Cliente") as Cliente
        val miBancoOperacional = MiBancoOperacional.getInstance(this)
        val cuentas = miBancoOperacional?.getCuentas(cliente)as ArrayList<Cuenta>

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cuentas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                // Cuando se selecciona una cuenta, obtén los movimientos y actualiza el RecyclerView
                selectedCuenta = cuentas?.get(position)
                actualizarMovimientos(selectedCuenta)
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Manejar caso en que no se ha seleccionado nada
            }
        }
    }

    private fun obtenerCuentasUsuario(): List<Cuenta>? {
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)
        // Obtener la lista de cuentas del usuario logueado (puedes implementar esto en tu propia lógica)
        return mbo?.getCuentas(null) as List<Cuenta>?
    }

    private fun obtenerMovimientos(cuenta: Cuenta): List<Movimiento>? {
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)
        // Obtener la lista de movimientos de la cuenta seleccionada
        return mbo?.getMovimientos(cuenta) as List<Movimiento>?
    }

    private fun actualizarMovimientos(cuenta: Cuenta?) {
        if (cuenta != null) {
            // Obtén la lista de movimientos de la cuenta seleccionada
            val movimientosList: List<Movimiento>? = obtenerMovimientos(cuenta)

            // Configura el adaptador para el RecyclerView
            val adapter = MovimientoAdapter(movimientosList.orEmpty() as ArrayList<Movimiento>)
            recyclerView.adapter = adapter
        }
    }
}
