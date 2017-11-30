package org.dean.mykotlinapplication.data

import com.google.gson.annotations.SerializedName


/**
 * Created by dean on 2017/11/27.
 */
data class Teaser(
        @SerializedName("images") val images: Images
)

data class Images(
        @SerializedName("teaser") val teaser: String
)

