package com.example.banco_jofago.fragments

import com.example.banco_jofago.pojo.Cuenta

interface CuentasListener {
    fun onCuentaSeleccionada(cuenta: Cuenta)
}