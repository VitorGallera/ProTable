package com.example.tablegroup.protable

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_inicio.*

class InicioActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        btnPlacar.setOnClickListener {

            val configuraPlacarPartida = Intent(this, ConfiguraPlacarActivity::class.java)
            startActivity(configuraPlacarPartida)
        }
    }
}
