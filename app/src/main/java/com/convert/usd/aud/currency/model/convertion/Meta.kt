package com.convert.usd.aud.currency.model.convertion
import com.google.gson.annotations.SerializedName


data class Meta (

  @SerializedName("code"       ) var code       : Int?    = null,
  @SerializedName("disclaimer" ) var disclaimer : String? = null

)