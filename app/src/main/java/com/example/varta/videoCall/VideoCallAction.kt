package com.example.varta.videoCall

sealed interface VideoCallAction {
    data object OnDisconnectClicked:VideoCallAction
    data object JoinCallClick:VideoCallAction
}