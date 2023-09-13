package com.ReXtOr.GroupChat.Rooms.di

import android.app.Activity
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.ActivityNavigatorExtras
import com.ReXtOr.GroupChat.Rooms.Network.ApiService
import com.ReXtOr.GroupChat.Rooms.Network.Websocket
import com.ReXtOr.GroupChat.Rooms.Network.webSocketInterface
import com.ReXtOr.GroupChat.Rooms.Repositort.Repository
import com.ReXtOr.GroupChat.Rooms.Repositort.RepositoryInterface
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
    fun providehhtpclient():HttpClient{
        return HttpClient(CIO){
            install(WebSockets)
        }
    }
    @Provides
    @Singleton
    fun provideApiServiceforrooms(): ApiService{

        return  Retrofit.Builder()
                .baseUrl("https://ancient-blade-398510.el.r.appspot.com")
//                .baseUrl("http://192.168.29.199:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)

    }

    @Provides
    @Singleton
    fun provideWebsocket(client: HttpClient): Websocket {
        return Websocket(client)
    }

    @Provides
    @Singleton
    fun provideRepo(
        apiService: ApiService
    ):RepositoryInterface{
        return Repository(
            apiService = apiService
        )
    }

//    @Provides
//    @Singleton
//    fun providesavedstate():SavedStateHandle{
//        return SavedStateHandle()
//    }
}