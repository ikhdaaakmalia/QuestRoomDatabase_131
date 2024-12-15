package com.ikhdaamel.p7

import android.app.Application
import com.ikhdaamel.p7.dependeciesinjection.ContainerApp
import com.ikhdaamel.p7.dependeciesinjection.InterfaceContainerApp

class KrsApp: Application() {                           //fungsinya utk menyimpan instance c\ContainerApp
    lateinit var containerApp: ContainerApp
    override fun onCreate(){
        super.onCreate()                                //membuat instace ContainerApp
        containerApp = ContainerApp(this)       //instance adalah obj yg dibuat dari class
    }
}