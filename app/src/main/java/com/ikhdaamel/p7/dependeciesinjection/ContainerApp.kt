package com.ikhdaamel.p7.dependeciesinjection

import android.content.Context
import com.ikhdaamel.p7.data.database.KrsDatabase
import com.ikhdaamel.p7.repository.LocalRepositoryMhs
import com.ikhdaamel.p7.repository.RepositoryMhs

interface InterfaceContainerApp {
    val repositoryMhs: RepositoryMhs
}

class ContainerApp(private val context: Context) : InterfaceContainerApp
{
    override val repositoryMhs: RepositoryMhs by lazy {
        LocalRepositoryMhs(KrsDatabase.getDatabase(context).mahasiswaDao())
    }
}