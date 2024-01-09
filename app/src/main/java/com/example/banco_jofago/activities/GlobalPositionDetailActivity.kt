package com.example.banco_jofago.activities

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.banco_jofago.R
import com.example.banco_jofago.databinding.ActivityGlobalPositionDetailBinding
import com.example.banco_jofago.fragments.CuentasMovimientosFragment
import com.example.banco_jofago.fragments.MovimientosListener
import com.example.banco_jofago.pojo.Cuenta
import com.example.banco_jofago.pojo.Movimiento
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat

class GlobalPositionDetailActivity : AppCompatActivity(), MovimientosListener {

    private lateinit var binding: ActivityGlobalPositionDetailBinding
    private var tipoMovimiento: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlobalPositionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cuentaSeleccionada = intent.getSerializableExtra("cuenta") as Cuenta
        val fragmentCuentasMovimientos: CuentasMovimientosFragment = CuentasMovimientosFragment.newInstance(cuentaSeleccionada)

        supportFragmentManager.beginTransaction().add(R.id.frgMovements, fragmentCuentasMovimientos).commit()

        binding.navegacion.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navegacion_todo -> {
                    replaceFragment(CuentasMovimientosFragment.newInstance(cuentaSeleccionada), this)
                    true
                }
                R.id.navegacion_0 -> {
                    tipoMovimiento = 0
                    replaceFragment(CuentasMovimientosFragment.newInstance(cuentaSeleccionada, tipoMovimiento), this)
                    true
                }
                R.id.navegacion_1 -> {
                    tipoMovimiento = 1
                    replaceFragment(CuentasMovimientosFragment.newInstance(cuentaSeleccionada, tipoMovimiento), this)
                    true
                }
                R.id.navegacion_2 -> {
                    tipoMovimiento = 2
                    replaceFragment(CuentasMovimientosFragment.newInstance(cuentaSeleccionada, tipoMovimiento), this)
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: CuentasMovimientosFragment, listener: MovimientosListener) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frgMovements, fragment)
            .commit()
        fragment.setListener(listener)
    }

    override fun onMovimientoSeleccionado(movimiento: Movimiento) {
        val dialogView = layoutInflater.inflate(R.layout.dialogo_movimiento, null)

        val formateador = SimpleDateFormat("dd/MM/yyyy")

        val textoInfo = dialogView.findViewById<TextView>(R.id.textoDialogo)
        textoInfo.text = "Id: ${movimiento.getId()} \n" +
                "Descripcion: ${movimiento.getDescripcion()} \n" +
                "Fecha: ${formateador.format(movimiento.getFechaOperacion())}"

        MaterialAlertDialogBuilder(this)
            .setView(dialogView)
            .setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, i ->
                dialog.cancel()
            })
            .setCancelable(false)
            .show()
    }
}
