package com.ReXtOr.GroupChat.Rooms.di

import android.app.Activity
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.ActivityNavigatorExtras
import com.ReXtOr.GroupChat.Rooms.Network.ApiService
import com.ReXtOr.GroupChat.Rooms.Network.Websocket
import com.ReXtOr.GroupChat.Rooms.Network.webSocketInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.WebSocketSession
import okhttp3.WebSocket
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun provideApiServiceforrooms(): ApiService? {

        return System.getenv("base_URL")?.let {
            Retrofit.Builder()
                .baseUrl(it)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

    @Provides
    @Singleton
    fun provideWebsocket(): webSocketInterface {
        return Websocket()
    }

//    @Provides
//    @Singleton
//    fun providesavedstate():SavedStateHandle{
//        return SavedStateHandle()
//    }
}