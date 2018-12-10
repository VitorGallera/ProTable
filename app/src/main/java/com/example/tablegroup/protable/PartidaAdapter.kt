package com.example.tablegroup.protable

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import kotlinx.android.synthetic.main.activity_partida_item.view.*

class PartidaAdapter(val context: Context, val partidas: List<Partida>):
        RecyclerView.Adapter<PartidaAdapter.ViewHolder>() {

    //salva a função do clique no item
    var clickListener: ((index: Int) -> Unit)? = null

    //método responsável por inflar as views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_partida_item, parent, false)
        return ViewHolder(view)
    }

    //retorna a quantidade de itens na lista
    override fun getItemCount(): Int {
        return partidas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(context, partidas[position], clickListener)
    }

    //configuração a função de clique nos itens
    fun setOnItemClickListener(clique: ((index: Int) -> Unit)){
        this.clickListener = clique
    }

    //referência para a view de cada item da lista
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(context:Context,
                     partida: Partida,
                     clickListener: ((index: Int) -> Unit)?) {
            itemView.tvJogador1.text = partida.Jogador1
            itemView.tvResultado.text = partida.ResultadoFinal
            itemView.tvJogador2.text = partida.Jogador2

            if(clickListener != null) {
                itemView.setOnClickListener {
                    clickListener.invoke(adapterPosition)
                }
            }
        }

    }
}