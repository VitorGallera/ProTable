package com.example.tablegroup.protable

import android.app.Activity
import android.content.Intent
import android.drm.DrmRights
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_placar.*

class PlacarActivity : Activity() {

    var jogador1: Jogador = Jogador("")
    var jogador2: Jogador = Jogador("")
    var fimSet: Boolean = false
    var partida: Partida = Partida(jogador1, jogador2)

    var set: Int = 1

    var pontosLeft: Int = 0
    var pontosRight: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placar)

        jogador1 = intent.getSerializableExtra(ConfiguraPlacarActivity.JOGADOR1) as Jogador
        jogador2 = intent.getSerializableExtra(ConfiguraPlacarActivity.JOGADOR2) as Jogador

        tvNomeJogadorLeft.text = jogador1.Nome
        tvNomeJogadorRight.text = jogador2.Nome

        tvJogadorLeft.setOnClickListener {
            pontosLeft += 1
            tvJogadorLeft.text = pontosLeft.toString()
            verificaPontos()
            if(fimSet) {
                if((set % 2) == 0)
                    jogador2.Sets += 1
                else
                    jogador1.Sets += 1
                finalSet()
            }
        }

        tvJogadorRight.setOnClickListener {
            pontosRight += 1
            tvJogadorRight.text = pontosRight.toString()
            verificaPontos()
            if(fimSet) {
                if((set % 2) == 0)
                    jogador1.Sets += 1
                else
                    jogador2.Sets += 1

                finalSet()
            }

        }

    }

    fun verificaPontos(){
        if(pontosLeft >= 11) {
            if((pontosLeft - pontosRight) >=2)
                fimSet = true
        }
        else if (pontosRight >= 11) {
            if((pontosRight - pontosLeft) >=2)
                fimSet = true
        }
    }

    fun finalSet(){
        when (set) {
            1 -> partida.Set1 = pontosLeft.toString() + "x" + pontosRight.toString()
            2 -> partida.Set2 = pontosLeft.toString() + "x" + pontosRight.toString()
            3 -> partida.Set3 = pontosLeft.toString() + "x" + pontosRight.toString()
            4 -> partida.Set4 = pontosLeft.toString() + "x" + pontosRight.toString()
            5 -> partida.Set5 = pontosLeft.toString() + "x" + pontosRight.toString()
        }
        pontosLeft = 0
        pontosRight = 0
        set += 1

        tvJogadorLeft.text = pontosLeft.toString()
        tvJogadorRight.text = pontosRight.toString()

        if((set % 2) == 0){
            tvNomeJogadorLeft.text = jogador2.Nome
            tvNomeJogadorRight.text = jogador1.Nome

            tvSetLeft.text = jogador2.Sets.toString()
            tvSetRight.text = jogador1.Sets.toString()
        }
        else{
            tvNomeJogadorLeft.text = jogador1.Nome
            tvNomeJogadorRight.text = jogador2.Nome

            tvSetLeft.text = jogador1.Sets.toString()
            tvSetRight.text = jogador2.Sets.toString()
        }
        fimSet = false

        if((jogador1.Sets == 3) || (jogador2.Sets == 3)){
            Toast.makeText(this, "Partida finalizada", Toast.LENGTH_LONG).show()
        }
    }
}
