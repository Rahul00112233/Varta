package com.example.varta


import com.example.varta.connect.ConnectViewModel
import com.example.varta.videoCall.VideoCallViewmodel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    factory {
        val app = androidContext().applicationContext as VideoCallingApp
        app.client
    }


    viewModelOf(::ConnectViewModel)
    viewModelOf(::VideoCallViewmodel)
}