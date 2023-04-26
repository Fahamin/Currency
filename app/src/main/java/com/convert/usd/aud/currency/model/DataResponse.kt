
import com.google.gson.annotations.SerializedName


data class DataResponse (

  @SerializedName("meta"     ) var meta     : Meta?     = Meta(),
  @SerializedName("response" ) var rateResponse : RateResponse<Any?>? = RateResponse()

)