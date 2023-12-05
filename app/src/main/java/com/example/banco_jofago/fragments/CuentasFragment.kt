package com.example.banco_jofago.fragments

import CuentasAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_jofago.adapters.OnClickListener
import com.example.banco_jofago.bd.MiBancoOperacional
import com.example.banco_jofago.databinding.FragmentCuentasBinding
import com.example.banco_jofago.pojo.Cliente
import com.example.banco_jofago.pojo.Cuenta

private const val ARG_CLIENTE = "cliente"

class CuentasFragment : Fragment(), OnClickListener {

    private lateinit var binding: FragmentCuentasBinding
    private lateinit var cuentaAdapter: CuentasAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var itemDecoration: DividerItemDecoration

    private lateinit var cliente: Cliente

    private lateinit var listener: CuentasListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cliente = it.getSerializable(ARG_CLIENTE) as Cliente
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstancestate: Bundle?
    ): View? {
        binding = FragmentCuentasBinding.inflate(inflater, container,  false)

        //Recuperar el valor
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(context)

        val listaCuentas: ArrayList<Cuenta> =
            mbo?.getCuentas(cliente as Cliente?) as ArrayList<Cuenta>
        val cuenta = Cuenta(12,"Santander","Nose","C/ juan","ES375893051785012",cliente,20f)

        listaCuentas.add(cuenta)



        cuentaAdapter = CuentasAdapter(listaCuentas)
        linearLayoutManager = LinearLayoutManager(context)
        itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        binding.recyclerViewCuentas.layoutManager = linearLayoutManager
        binding.recyclerViewCuentas.addItemDecoration(itemDecoration)
        binding.recyclerViewCuentas.adapter = cuentaAdapter


        return binding.root
    }
    fun setCuentasListener(listener: CuentasListener){
        this.listener=listener
    }
    override fun onClick(cuenta: Cuenta){
        if (listener != null){
            listener.onCuentaSeleccionada(cuenta)
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(c: Cliente) =
            CuentasFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CLIENTE,c)
                }
            }
    }




}

