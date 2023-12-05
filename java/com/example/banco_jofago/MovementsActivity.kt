package com.example.banco_jofago

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        spinner = findViewById(R.id.spinner)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedCuenta = parent?.getItemAtPosition(position) as Cuenta
                mostrarMovimientos()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //hay q crearlo vacío
            }
        }

        //mostrar cuentas en el spinner

        val cuentasList: List<Cuenta>? = obtenerCuentas()
        val cuentasAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cuentasList.orEmpty())
        cuentasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = cuentasAdapter
    }

    private fun obtenerCuentas(): List<Cuenta>? {
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)
        val cliente = intent.getSerializableExtra("Cliente") as Cliente
        return mbo?.getCuentas(cliente) as List<Cuenta>?
    }

    private fun mostrarMovimientos() {
        if (selectedCuenta != null) {
            val movimientosList: List<Movimiento>? = obtenerMovimientos(selectedCuenta!!)
            recyclerView.removeAllViews()
            for (movimiento in movimientosList.orEmpty()) {
                val cardView = createMovementCardView(movimiento)
                recyclerView.addView(cardView)
            }
        }
    }

    private fun obtenerMovimientos(cuenta: Cuenta): List<Movimiento>? {
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)
        return mbo?.getMovimientos(cuenta) as List<Movimiento>?
    }

    private fun createMovementCardView(movimiento: Movimiento): CardView {
        val cardView = CardView(this)
        val layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        cardView.layoutParams = layoutParams
        cardView.cardElevation = resources.getDimension(R.dimen.card)

        val textView = TextView(this)
        val textLayoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        textView.layoutParams = textLayoutParams
        textView.text = "${movimiento.getDescripcion()}\n ${movimiento.getFechaOperacion()}\n          ${movimiento.getImporte()}"
        cardView.addView(textView)

        return cardView
    }
}
