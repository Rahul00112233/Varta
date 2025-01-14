package com.example.varta.videoCall

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.getstream.video.android.core.StreamVideo
import kotlinx.coroutines.launch

class VideoCallViewmodel(
    private val videoClient: StreamVideo
): ViewModel() {

    var state by mutableStateOf(VideoCallState(
        call = videoClient.call("default", id = "main-room")
    ))
        private set

    fun onAction(action: VideoCallAction) {
        when(action) {
            VideoCallAction.JoinCallClick -> {
                joinCall()
            }
            VideoCallAction.OnDisconnectClicked -> {
                state.call.leave()
                videoClient.logOut()
                state = state.copy(callState = CallState.Ended)
            }
        }
    }

    private fun joinCall() {
        if (state.callState == CallState.Active) {
            return
        }
        viewModelScope.launch {
            state = state.copy(callState = CallState.Joining)

            val shouldCreate = videoClient
                .queryCalls(filters = emptyMap())
                .getOrNull()
                ?.calls
                ?.isEmpty() == true

            state.call.join(create = shouldCreate)
                .onSuccess {
                    state = state.copy(
                        callState = CallState.Active,
                        error = null
                    )
                }
                .onError {
                    state = state.copy(
                        error = it.message,
                        callState = null
                    )
                }
        }
    }

}