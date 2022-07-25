package com.yongjincompany.data.remote.datasource

import com.yongjincompany.data.remote.response.image.ImageResponse
import okhttp3.MultipartBody

interface RemoteImagesDataSource {

    suspend fun postImages(images : List<MultipartBody.Part>): ImageResponse
}