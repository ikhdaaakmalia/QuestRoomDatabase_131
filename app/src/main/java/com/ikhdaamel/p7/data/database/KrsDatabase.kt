package com.ikhdaamel.p7.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ikhdaamel.p7.data.dao.MahasiswaDao
import com.ikhdaamel.p7.data.entity.Mahasiswa

@Database(entities = [Mahasiswa::class], version = 1, exportSchema = false)  //export untuk export database, jika ada data entitas lain tinggal ditambahkan koma dlm [
abstract class KrsDatabase : RoomDatabase()
{
    abstract fun mahasiswaDao(): MahasiswaDao                 //agar database bisa ambil data di class mahasiswa
    companion object{                                        //obj yg akan dijalankan terus menerus
        @Volatile                                           //memastikan bahwa nilai variabel iinstance selalu sama di semua thread
        private var Instance: KrsDatabase? = null

        fun getDatabase(context: Context): KrsDatabase{
            return (Instance?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    KrsDatabase::class.java,                    //class database
                    "KrsDatabase"                         //nama databse
                )
                    .build().also { Instance = it }
            })
        }
    }
}