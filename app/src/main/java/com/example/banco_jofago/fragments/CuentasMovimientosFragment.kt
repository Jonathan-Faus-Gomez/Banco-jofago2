package com.example.banco_jofago.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_jofago.R
import com.example.banco_jofago.adapters.MovimientoAdapter
import com.example.banco_jofago.bd.MiBancoOperacional
import com.example.banco_jofago.databinding.FragmentCuentasBinding
import com.example.banco_jofago.databinding.FragmentCuentasMovimientosBinding
import com.example.banco_jofago.pojo.Cuenta
import com.example.banco_jofago.pojo.Movimiento

private const val ARG_CUENTA = "cuenta"
class CuentasMovimientosFragment : Fragment() {
    private lateinit var cuenta: Cuenta


    private lateinit var listener: MovimientosListener
    private lateinit var movimientoAdapter: MovimientoAdapter
    private lateinit var linearLayoutManager : LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration

    private lateinit var binding: FragmentCuentasMovimientosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cuenta= it.getSerializable(ARG_CUENTA) as Cuenta
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCuentasMovimientosBinding.inflate(inflater,container,false)

        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(context)
        if(mbo != null){
            context?.let { rellenarMovimientos(mbo,it) }
        }
       return binding.root
    }
    private fun rellenarMovimientos(mbo: MiBancoOperacional,context: Context){
        var listaMovimientos = mbo.getMovimientos(cuenta)
        movimientoAdapter = MovimientoAdapter(listaMovimientos as ArrayList <Movimiento>)
        linearLayoutManager = LinearLayoutManager(context)
        itemDecoration = DividerItemDecoration(context,DividerItemDecoration.VERTICAL)
    }

    fun setListener(listener: MovimientosListener){
        this.listener = listener
    }

    companion object {
        @JvmStatic
        fun newInstance(cu: Cuenta) =
            CuentasMovimientosFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CUENTA, cu)
                }
            }
    }
}