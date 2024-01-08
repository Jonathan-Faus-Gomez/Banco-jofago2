package com.example.banco_jofago.activities

import CuentasAdapter
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_jofago.R
import com.example.banco_jofago.adapters.MovimientoAdapter
import com.example.banco_jofago.bd.MiBancoOperacional
import com.example.banco_jofago.databinding.ActivityGlobalPositionBinding
import com.example.banco_jofago.fragments.CuentasFragment
import com.example.banco_jofago.fragments.CuentasListener
import com.example.banco_jofago.fragments.CuentasMovimientosFragment
import com.example.banco_jofago.pojo.Cliente
import com.example.banco_jofago.pojo.Cuenta
import com.example.banco_jofago.pojo.Movimiento
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat

class GlobalPositionActivity : AppCompatActivity() , CuentasListener {
    private lateinit var binding: ActivityGlobalPositionBinding

    //private lateinit var movimientoAdapter: MovimientoAdapter
    //private lateinit var linearLayoutManager : LinearLayoutManager
    //private lateinit var itemDecoration: DividerItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val mbo = MiBancoOperacional.getInstance(this)
        val cliente = intent.getSerializableExtra("Cliente") as Cliente
        //val cuentas = mbo?.getCuentas(cliente)as ArrayList<Cuenta>

        val fragmentCuenta: CuentasFragment = CuentasFragment.newInstance(cliente)

        supportFragmentManager.beginTransaction().add(R.id.frgCuentas,fragmentCuenta).commit()

        fragmentCuenta.setCuentasListener(this)

    }


    override fun onCuentaSeleccionada(cuenta: Cuenta) {
        if (cuenta != null) {
            var hayMovimientos = binding.frgCuentas?.let { supportFragmentManager.findFragmentById(it.id) } != null

            if(resources.configuration.screenLayout == 268435796){//Se muestra el contenido en la misma Activity

                val cuentasFragment = CuentasFragment()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frgCuentas, cuentasFragment)
                transaction.commitNow()

            }else{
                val i = Intent(this, GlobalPositionDetailActivity::class.java)
                i.putExtra("cuenta",cuenta)
                startActivity(i)
            }
        }
    }
    override fun onMovimientoSeleccionado(movimiento: Movimiento) {
        /**/
    }


}