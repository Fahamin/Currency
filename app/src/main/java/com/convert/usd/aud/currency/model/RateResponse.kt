
import com.google.gson.annotations.SerializedName


data class RateResponse<T>(

  @SerializedName("timestamp" ) var timestamp : Int?    = null,
  @SerializedName("date"      ) var date      : String? = null,
  @SerializedName("from"      ) var from      : String? = null,
  @SerializedName("to"        ) var to        : String? = null,
  @SerializedName("amount"    ) var amount    : Int?    = null,
  @SerializedName("value"     ) var value     : Double? = null

)