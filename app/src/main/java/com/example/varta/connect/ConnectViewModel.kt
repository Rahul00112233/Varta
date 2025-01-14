package com.example.varta.connect

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.varta.VideoCallingApp

class ConnectViewModel(
    private val app: Application
): AndroidViewModel(app) {

    var state by mutableStateOf(ConnectState())
        private set

    fun onAction(action: ConnectAction){
        when(action){
            ConnectAction.OnButtonClicked -> {
                room()
            }
            is ConnectAction.OnNameChange -> {
                state = state.copy(
                    name = action.name
                )
            }

        }
    }

    private fun room(){

        if(state.name.isBlank()){
            state = state.copy(
                error = "The username cannot be Empty!"
            )
        }


        (app as VideoCallingApp).initVideoCall(
            state.name
            //code = state.code
        )

        state = state.copy(
            isConnected = true
        )

    }
}