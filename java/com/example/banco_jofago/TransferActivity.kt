package com.example.banco_jofago

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.banco_jofago.databinding.ActivityTransferBinding

class TransferActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransferBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val radioGroup = findViewById<RadioGroup>(R.id.elegir)
        val spinner_propia = findViewById<Spinner>(R.id.spinner_propia)
        val text_ajena = findViewById<EditText>(R.id.text_ajena)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.cuenta_propia -> {
                    spinner_propia.visibility = View.VISIBLE
                    text_ajena.visibility = View.INVISIBLE
                }
                R.id.cuenta_ajena -> {
                    spinner_propia.visibility = View.INVISIBLE
                    text_ajena.visibility = View.VISIBLE
                }
            }
        }

        val btnEnviar = findViewById<Button>(R.id.boton_enviar)
        val btnCancelar = findViewById<Button>(R.id.boton_cancelar)


        val editTextCantidad = findViewById<EditText>(R.id.cantidad_text)
        val spinnerDivisa = findViewById<Spinner>(R.id.divisa)
        val justificante = findViewById<CheckBox>(R.id.justificante)


        val cuentasBancarias = listOf("ES77-4982-6317-7459-862-53784", "ES99-1269-3584-4702-3985-6471", "ES13-8732-0965-4182-7364-9501", "ES60-6025-4891-7324-9856-1703")
        val divisas = listOf("€", "$", "£", "¥")

        val cuentaOrigenSpinner = findViewById<Spinner>(R.id.spinner)
        val cuentaDestinoSpinner = findViewById<Spinner>(R.id.spinner_propia)
        val divisaSpinner = findViewById<Spinner>(R.id.divisa)

        // Configurar los adaptadores para los Spinners
        val cuentasBancariasAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cuentasBancarias)
        cuentasBancariasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        cuentaOrigenSpinner.adapter = cuentasBancariasAdapter
        cuentaDestinoSpinner.adapter = cuentasBancariasAdapter

        val divisasAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, divisas)
        divisasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        divisaSpinner.adapter = divisasAdapter

        btnEnviar.setOnClickListener {
            val cuentaOrigen = cuentaOrigenSpinner.selectedItem.toString()

            val cantidad = editTextCantidad.text.toString()
            val divisa = spinnerDivisa.selectedItem.toString()
            val enviarJustificante = justificante.isChecked

            val cuentaDestino = if (radioGroup.checkedRadioButtonId == R.id.cuenta_propia) {
                spinner_propia.selectedItem.toString()
            } else {
                text_ajena.text.toString()
            }
            // Crear un mensaje de resumen
            val resumen = "Cuenta origen: $cuentaOrigen\n" +
                    "Cuenta destino: $cuentaDestino\n" +
                    "Cantidad: $cantidad $divisa\n" +
                    "Enviar justificante: ${if (enviarJustificante) "Sí" else "No"}"

            // Mostrar un Toast con la información
            Toast.makeText(this, resumen, Toast.LENGTH_LONG).show()
        }

        btnCancelar.setOnClickListener {
            editTextCantidad.text.clear()
            text_ajena.text.clear()
            justificante.isChecked = false
        }
    }
}
