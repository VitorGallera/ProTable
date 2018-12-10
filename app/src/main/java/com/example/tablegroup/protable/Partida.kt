package com.example.tablegroup.protable

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity
class Partida (var Jogador1: String?,
               var Jogador2: String?,
               var Set1: String? = "0x0",
               var Set2: String? = "0x0",
               var Set3: String? = "0x0",
               var Set4: String? = "0x0",
               var Set5: String? = "0x0",
               var ResultadoFinal: String? = "",
               @PrimaryKey(autoGenerate = true)
               var id: Int = 0): Serializable