package com.ikhdaamel.p7.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mahasiswa")  //nama tabel
data class Mahasiswa (
    @PrimaryKey                   //primary key diberi tanda pada atas kolom yg mau dijadikan primary key
    val nim: String,                //kolom
    val nama: String,               //@foreignkey = diberikan pada kolom yg diambil dari primari lain
    val alamat: String,
    val jenisKelamin: String,
    val kelas: String,
    val angkatan: String
)