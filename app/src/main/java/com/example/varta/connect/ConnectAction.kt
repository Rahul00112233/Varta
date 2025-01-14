package com.example.varta.connect

sealed interface ConnectAction {
    data class OnNameChange(val name: String): ConnectAction
    //data class OnCodeChange(val code: String): ConnectAction
    data object OnButtonClicked: ConnectAction
}