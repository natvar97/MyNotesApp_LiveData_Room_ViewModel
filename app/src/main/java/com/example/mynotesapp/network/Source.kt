package com.example.mynotesapp.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Source {
    @Expose
    @SerializedName("name")
    var name: String? = null

    @Expose
    @SerializedName("id")
    var id: String? = null
}