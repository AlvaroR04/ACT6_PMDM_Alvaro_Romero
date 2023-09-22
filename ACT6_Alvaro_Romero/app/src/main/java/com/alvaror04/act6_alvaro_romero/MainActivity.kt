package com.alvaror04.act6_alvaro_romero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alvaror04.act6_alvaro_romero.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var tiradas = 0
        var puntuacionJugador = 0
        var puntuacionMaquina = 0

        binding.bVolverJugar.isEnabled = false

        binding.bLanzar.setOnClickListener {
            val tmpDadoJug = Random.nextInt(1, 7)
            val tmpDadoMaq = Random.nextInt(1, 7)

            binding.ivDadoJug.setImageResource(obtenerDado(tmpDadoJug))
            binding.ivDadoMaq.setImageResource(obtenerDado(tmpDadoMaq))

            puntuacionJugador += tmpDadoJug
            puntuacionMaquina += tmpDadoMaq

            binding.tvPuntosJug.text = puntuacionJugador.toString()
            binding.tvPuntosMaq.text = puntuacionMaquina.toString()

            tiradas++

            if(tiradas > 5) {
                if(puntuacionJugador > puntuacionMaquina) {
                    mostrarToast(application.getString(R.string.player_won))
                } else if(puntuacionJugador < puntuacionMaquina) {
                    mostrarToast(application.getString(R.string.machine_won))
                } else {
                    mostrarToast(application.getString(R.string.draw))
                }

                binding.bLanzar.isEnabled = false
                binding.bVolverJugar.isEnabled = true
            }
        }

        binding.bVolverJugar.setOnClickListener {
            finish()
            startActivity(intent)
        }
    }

    //--- INTERNO ---

    fun mostrarToast(msg: String) {
        Toast.makeText(
            this,
            msg,
            Toast.LENGTH_LONG
        ).show()
    }

    fun obtenerDado(valor : Int) : Int {
        var res = 0

        res = when(valor) {
            2 -> R.drawable.dado2
            3 -> R.drawable.dado3
            4 -> R.drawable.dado4
            5 -> R.drawable.dado5
            6 -> R.drawable.dado6
            else -> R.drawable.dado1
        }

        return res
    }
}