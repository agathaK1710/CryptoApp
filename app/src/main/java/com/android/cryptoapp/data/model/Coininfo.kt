package com.android.cryptoapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Coininfo(
    @SerializedName("Name")
    @Expose
    val name: String? = null,
)