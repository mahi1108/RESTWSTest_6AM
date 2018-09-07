package cubex.mahesh.restwstest.beans

import com.google.gson.annotations.SerializedName

data class TrainStatusBean(@SerializedName("response_code")
                           val responseCode: Int = 0,
                           @SerializedName("route")
                           val route: List<RouteItem>?,
                           @SerializedName("position")
                           val position: String = "",
                           @SerializedName("current_station")
                           val currentStation: CurrentStation,
                           @SerializedName("debit")
                           val debit: Int = 0,
                           @SerializedName("train")
                           val train: Train,
                           @SerializedName("start_date")
                           val startDate: String = "")