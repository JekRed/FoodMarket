package com.example.foodmarketonline.model.reponse

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Wrapper<T> {
    @Expose
    @SerializedName("meta")
    var meta: Meta ?= null
    @Expose
    @SerializedName("data")
    var data: T? = null
}