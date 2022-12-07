package com.joduma.equipodinamita.activities.products.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.joduma.equipodinamita.activities.products.Producto


@Dao
interface ProductosDao {
    @Query( "SELECT * FROM productos")
    fun getAll(): LiveData<List<Producto>>

    @Query("SELECT * FROM productos WHERE idProducto = :id")
    fun get (id: Int): LiveData<Producto>

    @Insert
    fun insertAll(vararg productos: Producto): List<Long>


    @Update
    fun update(producto: Producto)

    @Delete
    fun delete(producto: Producto)



}