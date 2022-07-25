package com.yongjincompany.data.remote.response.image

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("image_url") val imageUrl: List<String>
)