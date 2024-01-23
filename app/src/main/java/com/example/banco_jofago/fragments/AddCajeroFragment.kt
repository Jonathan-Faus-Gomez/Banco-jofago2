package com.example.banco_jototo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.banco_jofago.bd.CajeroApplication
import com.example.banco_jofago.databinding.FragmentAddCajeroBinding
import com.example.banco_jofago.entities.CajeroEntity




class AddCajeroFragment : Fragment() {


    private lateinit var binding: FragmentAddCajeroBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCajeroBinding.inflate(inflater, container, false)

        binding.save.setOnClickListener {
            var direccion = binding.textViewAddress.text.toString().trim()
            var latitud: Double = binding.textViewLatitude.text.toString().trim().toDouble()
            var longuitud: Double = binding.textViewLength.text.toString().trim().toDouble()

            val cajero = CajeroEntity(direccion = direccion, latitud = latitud, longitud = longuitud)

            Thread{
                CajeroApplication.db.cajeroDao().addCajero(cajero) // crear cajero

                activity?.runOnUiThread {
                    binding.textViewAddress.text= ""
                    binding.textViewLatitude.text = ""
                    binding.textViewLength.text = ""
                }


            }.start()

        }

        binding.cancel.setOnClickListener {
            binding.textViewAddress.text= ""
            binding.textViewLatitude.text = ""
            binding.textViewLength.text = ""
        }




        return binding.root
    }

    companion object {


        @JvmStatic
        fun newInstance() =
            AddCajeroFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}