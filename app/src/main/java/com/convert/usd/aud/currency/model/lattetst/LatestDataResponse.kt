
import com.google.gson.annotations.SerializedName


data class LatestDataResponse (

  @SerializedName("date"  ) var date  : String? = null,
  @SerializedName("base"  ) var base  : String? = null,
  @SerializedName("rates" ) var rates : Rates?  = Rates()

)