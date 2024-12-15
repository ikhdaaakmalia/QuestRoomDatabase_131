package com.ikhdaamel.p7.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikhdaamel.p7.data.entity.Mahasiswa
import com.ikhdaamel.p7.repository.LocalRepositoryMhs
import com.ikhdaamel.p7.repository.RepositoryMhs
import kotlinx.coroutines.launch

class MahasiswaViewModel(private val repositoryMhs: RepositoryMhs): ViewModel() {
    var uiState by mutableStateOf(MhsUIState())

    //memperbarui state berdasarkan input pengguna
    fun updateState(mahasiswaEvent: MahasiswaEvent) {
        uiState = uiState.copy(
            mahasiswaEvent = mahasiswaEvent
        )
    }
    //validasi data input pengguna
    private fun validateFields(): Boolean{
        val event = uiState.mahasiswaEvent                              //dipanggil untuk text field
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty()) null else "NIM tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            jenisKelamin = if (event.jeniskelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",
            kelas = if (event.kelas.isNotEmpty()) null else "Kelas tidak boleh kosong",
            angkatan = if (event.angkatan.isNotEmpty()) null else "Angkatan tidak boleh kosong",
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData() {
        val currentEvent = uiState.mahasiswaEvent
        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositoryMhs.insertMhs(currentEvent.toMahasiswaEntity())       //jika validasi benar maka akan panggil dari repository mahasiswa
                    uiState = uiState.copy(
                        snackBarMessage = "Data Berhasil Disimpan",
                        mahasiswaEvent = MahasiswaEvent(),  //rest input form
                        isEntryValid = FormErrorState()
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackBarMessage = "Data Gagal Disimpan"
                    )
                }
            }
        }
        else{
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid, periksa kembali data anda"
            )
        }
    }
    //restet pesan snackbar setelah ditampilkan
    fun resetSnackBarMessage(){
        uiState = uiState.copy(snackBarMessage = null)
    }
}
//merubah state (menghandle perubahan tampilan)
data class MhsuiState(
    val mahasiswaEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryvalid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,                        //pop up
)

//validasi eror
data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val jenisKelamin: String? = null,
    val alamat: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null,
){
    fun isValid(): Boolean{
        return nim == null && nama == null && jenisKelamin == null
                && alamat == null && kelas == null && angkatan == null
    }
}

//menyimpan input form ke dalam entity
fun MahasiswaEvent.toMahasiswaEntity(): Mahasiswa = Mahasiswa(  //entity = model, akan digunaan saat memanggil repository
    nim = nim,
    nama = nama,
    jenisKelamin = jeniskelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan
)

//data class variabel yg menyimpan data input form (pengganti variabel dlm view / by remember) yg akan hendel masing" text field
data class MahasiswaEvent(          //(Event = txt field)
    val nim: String ="",
    val nama: String ="",
    val jeniskelamin: String ="",
    val alamat: String ="",
    val kelas: String ="",
    val angkatan: String =""
)