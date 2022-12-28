package com.yongjincompany.data.remote.datasource

import kotlinx.coroutines.flow.SharedFlow

interface RemoteSocketDataSource {
    fun disconnectSocket()

    fun connectSocket()

    fun createRoom(roomName: String)

    fun receiveCreateRoom(): SharedFlow<String>

    fun joinRoom(roomId: Long)

    fun receiveJoinRoom(): SharedFlow<String>

    fun chatting(emoji: String, char: String)

    fun receiveChatting(): SharedFlow<String>

    fun receiveError(): List<String>
}