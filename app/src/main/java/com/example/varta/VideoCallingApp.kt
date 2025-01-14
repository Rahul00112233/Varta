package com.example.varta

import android.app.Application
import io.getstream.video.android.core.StreamVideo
import io.getstream.video.android.core.StreamVideoBuilder
import io.getstream.video.android.model.User
import io.getstream.video.android.model.UserType
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VideoCallingApp: Application() {

    private var currentUsername: String? = null
    private var currentCode: String? = null
    var client: StreamVideo? = null

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@VideoCallingApp)
            modules(appModule)

        }
    }

    fun initVideoCall(username: String){
        if(username != currentUsername || client == null){
            StreamVideo.removeClient()
            currentUsername = username
            //currentCode = code

            client = StreamVideoBuilder(
                context = this,
                apiKey = "2w4va93ahmw7",
                user = User(
                    id = username,
                    name = username,
                    type = UserType.Guest,
                )
            ).build()
        }
    }

}