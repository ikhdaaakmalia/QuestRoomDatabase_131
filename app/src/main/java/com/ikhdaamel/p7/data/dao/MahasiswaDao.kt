package com.ikhdaamel.p7.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ikhdaamel.p7.data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

@Dao
interface MahasiswaDao {
    @Query("select * from mahasiswa")                       //get all data mahasiswa dimulai dari dao
    fun getAllMahasiswa() : Flow <List<Mahasiswa>>          //mengembalikan nilai yg dibungkus dlm flow (list mahasiswa)

    @Query ("SELECT * FROM mahasiswa WHERE nim = :nim")     //get mahasiswa
    fun getMahasiswa (nim: String) : Flow<Mahasiswa>

    @Delete                                                 //delete mahasiswa
    suspend fun deleteMahasiswa (mahasiswa: Mahasiswa)

    @Update                                                 //update mahasiswa
    suspend fun updateMahasiswa (mahasiswa: Mahasiswa)

    @Insert                                                 //@Update atau apapun bisa aja tinggal ditambahkan tag nya aja
    suspend fun insertMahasiswa(
        mahasiswa: Mahasiswa                               //memanggil entitasnya
    )

}