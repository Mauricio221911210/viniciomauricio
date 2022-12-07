package com.joduma.equipodinamita.activities.products.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.joduma.equipodinamita.activities.products.Producto


@Database(entities = [Producto::class], version = 1 )
abstract class AppDatabase : RoomDatabase() {
    abstract fun productos(): ProductosDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val temInstance = INSTANCE

            if (temInstance != null) {
                return temInstance
            }

            synchronized( this) {
                val instace = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "vini"
                ).build()

                INSTANCE = instace

                return instace
            }

        }
    }
}