package com.example.tablegroup.protable

import android.arch.persistence.room.*

@Dao
interface PartidaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(partida: Partida)

    @Query("SELECT * FROM partida")
    fun getAll(): List<Partida>

    @Delete
    fun delete(partida: Partida)

    @Query("SELECT * FROM partida WHERE id = :partidaId LIMIT 1")
    fun getPartida(partidaId: Int): Partida

}