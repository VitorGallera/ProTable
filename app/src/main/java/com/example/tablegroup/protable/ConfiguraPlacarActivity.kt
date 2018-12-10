package com.example.tablegroup.protable

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_configura_placar.*

class ConfiguraPlacarActivity : Activity() {

    companion object {
        const val JOGADOR1: String  = "jogador1"
        const val JOGADOR2: String  = "jogador2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configura_placar)

        btnIniciar.setOnClickListener {
            val placar = Intent(this, PlacarActivity::class.java)
            val jogador1: Jogador = Jogador(etJogador1.text.toString())
            val jogador2: Jogador = Jogador(etJogador2.text.toString())
            placar.putExtra(JOGADOR1, jogador1)
            placar.putExtra(JOGADOR2, jogador2)
            startActivity(placar)
            //finish()
        }
    }
}
