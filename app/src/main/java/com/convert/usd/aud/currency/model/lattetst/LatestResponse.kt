

import com.google.gson.annotations.SerializedName


data class LatestResponse (

  @SerializedName("meta"     ) var meta2     : Meta2?     = Meta2(),
  @SerializedName("response" ) var response : LatestDataResponse? = LatestDataResponse()

)