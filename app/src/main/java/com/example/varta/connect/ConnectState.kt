package com.example.varta.connect

data class ConnectState(
    val name: String = "",
    //val code: String = "",
    val isConnected: Boolean = false,
    val error: String? = null
)
