package com.ikhdaamel.p7.repository

import com.ikhdaamel.p7.data.dao.MahasiswaDao
import com.ikhdaamel.p7.data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMhs ( private val mahasiswaDao: MahasiswaDao) : RepositoryMhs{   //berparameter dao krn diimplementasi dari dao
    override suspend fun insertMhs(mahasiswa: Mahasiswa){
        mahasiswaDao.insertMahasiswa(mahasiswa)
    }

    override fun getAllMahasiswa(): Flow<List<Mahasiswa>> {
        return mahasiswaDao.getAllMahasiswa()
    }

    override fun getMhs(nim: String): Flow<Mahasiswa> {
        return mahasiswaDao.getMahasiswa(nim)
    }

    override suspend fun deleteMhs(mahasiswa: Mahasiswa) {

    }

    override suspend fun updateMhs(mahasiswa: Mahasiswa) {

    }

}
