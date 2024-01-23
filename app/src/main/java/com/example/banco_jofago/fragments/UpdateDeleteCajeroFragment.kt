package com.example.banco_jototo.fragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.banco_jofago.bd.CajeroApplication
import com.example.banco_jofago.databinding.FragmentUpdateDeleteCajeroBinding
import com.example.banco_jofago.entities.CajeroEntity



private const val ARG_CAJERO = "cajero"

class UpdateDeleteCajeroFragment : Fragment() {

    private lateinit var binding: FragmentUpdateDeleteCajeroBinding

    private lateinit var cajero: CajeroEntity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cajero = it.getSerializable(ARG_CAJERO) as CajeroEntity
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUpdateDeleteCajeroBinding.inflate(inflater, container, false)

        if (cajero != null){

            binding.textViewAddress.text = Editable.Factory.getInstance().newEditable(cajero.direccion.toString())
            binding.textViewLatitude.text = Editable.Factory.getInstance().newEditable(cajero.latitud.toString())
            binding.textViewLength.text = Editable.Factory.getInstance().newEditable(cajero.longitud.toString())

        }

        binding.save.setOnClickListener {




                var direccion = binding.textViewAddress.text.toString().trim()
                var latitud = binding.textViewLatitude.text.toString().trim().toDouble()
                var longitud = binding.textViewLength.text.toString().trim().toDouble()

                val cajero = CajeroEntity(id = id, direccion = direccion, latitud = latitud, longitud = longitud)

                Thread{
                    CajeroApplication.db.cajeroDao().updateCajero(cajero)

                    activity?.runOnUiThread {
                        binding.textViewAddress.text = ""
                        binding.textViewLatitude.text = ""
                        binding.textViewLength.text = ""
                    }
                }.start()


        }
        binding.delete.setOnClickListener {

            var cajeroEliminar = CajeroEntity(id = id, direccion = "", latitud = 0.0, longitud = 0.0)

            if (cajero != null){
                val direccion = binding.textViewAddress.text.toString().trim()
                val latitud = binding.textViewLatitude.text.toString().trim().toDouble()
                val longuitud = binding.textViewLength.text.toString().trim().toDouble()

                cajeroEliminar = CajeroEntity(id = id, direccion = direccion, latitud = latitud, longitud = longuitud)

            }

            Thread{
                CajeroApplication.db.cajeroDao().deleteCajero(cajeroEliminar)

                activity?.runOnUiThread {
                    binding.textViewAddress.text = ""
                    binding.textViewLatitude.text = ""
                    binding.textViewLength.text = ""
                }

            }.start()
        }



        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(d: Boolean, cajero: CajeroEntity) =
            UpdateDeleteCajeroFragment().apply {
                arguments = Bundle().apply {

                    putSerializable(ARG_CAJERO, cajero)
                }
            }
    }
}