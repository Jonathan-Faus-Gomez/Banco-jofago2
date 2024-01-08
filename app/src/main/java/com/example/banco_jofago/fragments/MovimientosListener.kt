package com.example.banco_jofago.fragments

import com.example.banco_jofago.pojo.Cuenta
import com.example.banco_jofago.pojo.Movimiento

interface MovimientosListener {
    fun onMovimientoSeleccionado(movimiento: Movimiento)
}