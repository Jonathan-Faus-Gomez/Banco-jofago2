package com.example.banco_jofago

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.banco_jofago.bd.MiBancoOperacional
import com.example.banco_jofago.databinding.ActivityLoginBinding
import com.example.banco_jofago.pojo.Cliente

class LoginActivity : AppCompatActivity(){
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.botonEntrar.setOnClickListener {
            val dni = binding.usuario.editText?.text.toString()
            val password = binding.password.editText?.text.toString()

            if (dni.isEmpty() || password.isEmpty()) {
                binding.usuario.error="Por favor rellene el usuario"
                binding.password.error="Por favor rellene la contraseña"
            } else {


                var cliente = Cliente()
                cliente.setNif(dni)
                cliente.setClaveSeguridad(password)

                val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

                val resultado = mbo?.login(cliente) ?: -1

                if(resultado == -1) {
                    Toast.makeText(this, "ERROR: No se ha podido iniciar sesión", Toast.LENGTH_LONG).show()
                }else{
                    val intent = Intent(this, MainActivity::class.java )
                    intent.putExtra("Cliente",cliente)
                    startActivity(intent)
                }

            }
        }

        binding.botonSalir.setOnClickListener {
            finish()
        }
    }
}