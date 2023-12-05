package com.example.banco_jofago

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_jofago.bd.MiBancoOperacional
import com.example.banco_jofago.databinding.ActivityGlobalPositionBinding
import com.example.banco_jofago.pojo.Cliente
import com.example.banco_jofago.pojo.Cuenta
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
class GlobalPositionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGlobalPositionBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cliente = intent.getSerializableExtra("Cliente") as Cliente

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Obtiene la lista de cuentas del cliente
        val cuentasList: List<Cuenta>? = obtenerCuentas(cliente)

        // Configura el adaptador para el RecyclerView
        for (cuenta in cuentasList.orEmpty()) {
            val cardView = createAccountCardView(cuenta)
            recyclerView.addView(cardView)
        }
    }

    private fun obtenerCuentas(cliente: Cliente): List<Cuenta>? {
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)
        return mbo?.getCuentas(cliente) as List<Cuenta>?
    }

    private fun createAccountCardView(cuenta: Cuenta): MaterialCardView {
        val cardView = MaterialCardView(this)
        val layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        cardView.layoutParams = layoutParams
        cardView.cardElevation = resources.getDimension(R.dimen.card)

        val textView = MaterialTextView(this)
        val textLayoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        textView.layoutParams = textLayoutParams
        textView.text = "Número de cuenta: ${cuenta.getNumeroCuenta()}\nSaldo: ${cuenta.getSaldoActual()}"
        cardView.addView(textView)

        return cardView
    }
}
