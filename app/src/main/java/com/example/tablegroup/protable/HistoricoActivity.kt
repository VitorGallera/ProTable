package com.example.tablegroup.protable

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_historico.*
import org.jetbrains.anko.activityUiThreadWithContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class HistoricoActivity : Activity() {

    var listaPartidas: MutableList<Partida> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)
    }

    override fun onResume() {
        super.onResume()

        val partidaDao = AppDatabase.getInstance(this).partidaDao()
        doAsync {
            listaPartidas = partidaDao.getAll() as MutableList<Partida>

            activityUiThreadWithContext {
                val adapter = PartidaAdapter(this, listaPartidas)

                //configura o clique em cada item do RecyclerView
                adapter.setOnItemClickListener { indexPartidaClicado ->
                    // val editaContatinho = Intent(this, CadastraContatinhoActivity::class.java)
                    //editaContatinho.putExtra(CadastraContatinhoActivity.CONTATINHO, listaContatinhos.get(indexContatinhoClicado))
                    //startActivity(editaContatinho)
                    true
                }

                adapter.configuraCliqueLongo {indexPartidaClicada ->
                    doAsync {
                        partidaDao.delete(listaPartidas.get(indexPartidaClicada))
                        uiThread {
                            onResume()
                        }
                    }
                    true
                }

                val layoutManager = LinearLayoutManager(this)

                rvHistorico.adapter = adapter
                rvHistorico.layoutManager = layoutManager
            }
        }
    }
}
