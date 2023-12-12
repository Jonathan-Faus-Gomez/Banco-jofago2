// MovementAdapter.kt
package com.example.banco_jofago.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_jofago.databinding.ItemMovementBinding
import com.example.banco_jofago.pojo.Movimiento
//poner click listener
class MovimientoAdapter(private val movimientosList: ArrayList<Movimiento>) :
    RecyclerView.Adapter<MovimientoAdapter.MovementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementViewHolder {
        val binding = ItemMovementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovementViewHolder, position: Int) {
        val movimiento = movimientosList[position]
        holder.bind(movimiento)
    }

    override fun getItemCount(): Int {
        return movimientosList.size
    }

    class MovementViewHolder(private val binding: ItemMovementBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movimiento: Movimiento) {
            binding.movementDescripcionTextView.text = movimiento.getDescripcion()
            binding.movementFechaTextView.text = movimiento.getFechaOperacion().toString()
            binding.movementImporteTextView.text = movimiento.getImporte().toString()
        }
    }
}

