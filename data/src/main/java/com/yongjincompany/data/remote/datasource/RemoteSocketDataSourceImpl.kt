package com.yongjincompany.data.remote.datasource

import io.socket.client.Socket
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class RemoteSocketDataSourceImpl @Inject constructor(
    private val socket: Socket
) : RemoteSocketDataSource {
    override fun disconnectSocket() {
        TODO("Not yet implemented")
    }

    override fun connectSocket() {
        TODO("Not yet implemented")
    }

    override fun createRoom(roomName: String) {
        TODO("Not yet implemented")
    }

    override fun receiveCreateRoom(): SharedFlow<String> {
        TODO("Not yet implemented")
    }

    override fun joinRoom(roomId: Long) {
        TODO("Not yet implemented")
    }

    override fun receiveJoinRoom(): SharedFlow<String> {
        TODO("Not yet implemented")
    }

    override fun chatting(emoji: String, char: String) {
        TODO("Not yet implemented")
    }

    override fun receiveChatting(): SharedFlow<String> {
        TODO("Not yet implemented")
    }

    override fun receiveError(): List<String> {
        TODO("Not yet implemented")
    }
}