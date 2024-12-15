package com.ikhdaamel.p7.repository

import com.ikhdaamel.p7.data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

interface RepositoryMhs {                           //panduan apa yg mau dilakukan (dgn interface) yg akan digunakan di local repo
    suspend fun insertMhs(mahasiswa: Mahasiswa)     //jika di dao td pake suspand maka di repo jg.
                                                    //suspend digunakan dlm oprasi yg berat (create, edit, delete)
    fun getAllMahasiswa() : Flow<List<Mahasiswa>>   //get all mahasiswa

    fun getMhs (nim: String) : Flow<Mahasiswa>      //get mahasiswa

    suspend fun deleteMhs (mahasiswa: Mahasiswa)    //delete mahasiswa

    suspend fun updateMhs (mahasiswa: Mahasiswa)      //update mahasiswa
}