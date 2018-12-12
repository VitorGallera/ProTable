package com.example.tablegroup.protable

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_descricao_partida.*

class DescricaoPartidaActivity : Activity() {

    companion object {
        val PARTIDA: String = "partida"
    }

    var partida: Partida = Partida()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descricao_partida)

        partida = intent.getSerializableExtra(PARTIDA) as Partida

        tvJogador1Descricao.text = partida.Jogador1
        tvJogador2Descricao.text = partida.Jogador2

        tv1Set.text = partida.Set1
        tv2Set.text = if (partida.Set2.equals("0x0")) "" else partida.Set2
        tv3Set.text = if (partida.Set3.equals("0x0")) "" else partida.Set3
        tv4Set.text = if (partida.Set4.equals("0x0")) "" else partida.Set4
        tv5Set.text = if (partida.Set5.equals("0x0")) "" else partida.Set5

        tvResultadoFinal.text = partida.ResultadoFinal
    }
}
