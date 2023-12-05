package com.example.banco_jofago


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.banco_jofago.databinding.ActivityMainBinding
import com.example.banco_jofago.pojo.Cliente

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val cliente = intent.getSerializableExtra("Cliente") as Cliente

        // Actualiza el mensaje de bienvenida con el número de DNI
        binding.bienvenido.text = cliente.getNombre()


        binding.cambiarPassword.setOnClickListener{
            val intent = Intent(this, CambiarPasswordActivity::class.java )
            intent.putExtra("Cliente",cliente)
            startActivity(intent)
        }
        binding.transferencias.setOnClickListener{
            val intent = Intent(this, TransferActivity::class.java )
            intent.putExtra("Cliente",cliente)
            startActivity(intent)
        }
        binding.posicion.setOnClickListener{
            val intent = Intent(this, GlobalPositionActivity::class.java )
            intent.putExtra("Cliente",cliente)
            startActivity(intent)
        }
        binding.movimientos.setOnClickListener{
            val intent = Intent(this, MovementsActivity::class.java )
            intent.putExtra("Cliente",cliente)
            startActivity(intent)
        }
    }
}