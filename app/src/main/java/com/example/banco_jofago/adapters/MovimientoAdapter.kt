// MovementAdapter.kt
package com.example.banco_jofago.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_jofago.databinding.ItemMovementBinding
import com.example.banco_jofago.pojo.Movimiento
import com.example.banco_jofago.R
//poner click listener

class MovimientoAdapter(private val movimientos: ArrayList<Movimiento>, private val listener: OnClickListenerMovimiento? = null):
    RecyclerView.Adapter<MovimientoAdapter.ViewHolder>(){
    private lateinit var context: Context
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val binding = ItemMovementBinding.bind(view)

        fun setListener(movimiento: Movimiento){
            binding.root.setOnClickListener {
                listener?.onClick(movimiento)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_movement, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movimientos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var movimiento = movimientos.get(position)

        with(holder){
            setListener(movimiento)

            binding.movementDescripcionTextView.text = movimiento.getDescripcion()
            if (movimiento.getImporte() >= 0){
                binding.movementImporteTextView.setTextColor(ContextCompat.getColor(context, R.color.positivo))
            }else{
                binding.movementImporteTextView.setTextColor(ContextCompat.getColor(context, R.color.negativo))
            }

            binding.movementFechaTextView.text = movimiento.getFechaOperacion().toString()
            binding.movementImporteTextView.text = movimiento.getImporte().toString()

        }
    }

}

/*
*
*
            binding.movementDescripcionTextView.text = movimiento.getDescripcion()
            binding.movementFechaTextView.text = movimiento.getFechaOperacion().toString()
            binding.movementImporteTextView.text = movimiento.getImporte().toString()
*
*/