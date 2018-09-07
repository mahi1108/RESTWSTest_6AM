package cubex.mahesh.restwstest

import cubex.mahesh.restwstest.beans.TrainStatusBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RailwayAPI {

    @GET("v2/live/train/{tno}/date/{date}/apikey/7opzpsj744/")
    fun getTrainStatus(@Path("tno") t:String,
                      @Path("date") d:String):Call<TrainStatusBean>
}