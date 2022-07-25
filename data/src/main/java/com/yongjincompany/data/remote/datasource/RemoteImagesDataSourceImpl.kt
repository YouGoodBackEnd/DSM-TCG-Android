package com.yongjincompany.data.remote.datasource

import com.yongjincompany.data.remote.api.ImagesApi
import com.yongjincompany.data.remote.response.image.ImageResponse
import com.yongjincompany.data.util.HttpHandler
import okhttp3.MultipartBody
import javax.inject.Inject

class RemoteImagesDataSourceImpl @Inject constructor(
    private val imagesApi : ImagesApi
): RemoteImagesDataSource {
    override suspend fun postImages(images: List<MultipartBody.Part>): ImageResponse =
        HttpHandler<ImageResponse>()
            .httpRequest { imagesApi.postImages(images) }
            .sendRequest()
}