package com.example.tablegroup.protable

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_placar.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PlacarActivity : Activity() {

    var jogador1: Jogador = Jogador()
    var jogador2: Jogador = Jogador()
    var fimSet: Boolean = false
    var partida: Partida = Partida(jogador1.Nome, jogador2.Nome)

    var set: Int = 1

    var pontosLeft: Int = 0
    var pontosRight: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placar)

        jogador1 = intent.getSerializableExtra(ConfiguraPlacarActivity.JOGADOR1) as Jogador
        jogador2 = intent.getSerializableExtra(ConfiguraPlacarActivity.JOGADOR2) as Jogador

        partida.Jogador1 = jogador1.Nome
        partida.Jogador2 = jogador2.Nome

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
            //Lado esquerda das variaveis Set'is é do jogador1
            1 -> partida.Set1 = pontosLeft.toString() + "x" + pontosRight.toString()
            2 -> partida.Set2 = pontosRight.toString() + "x" + pontosLeft.toString()
            3 -> partida.Set3 = pontosLeft.toString() + "x" + pontosRight.toString()
            4 -> partida.Set4 = pontosRight.toString() + "x" + pontosLeft.toString()
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

        if((jogador1.Sets >= 3) || (jogador2.Sets >= 3)){

            partida.ResultadoFinal = jogador1.Sets.toString() + "x" + jogador2.Sets.toString()
            Toast.makeText(this, "Partida finalizada", Toast.LENGTH_LONG).show()

            val partidaDAO: PartidaDAO = AppDatabase.getInstance(this).partidaDao()
            doAsync{
                partidaDAO.insert(partida!!)
                uiThread{
                    finish()
                }
            }
        }
    }
}
