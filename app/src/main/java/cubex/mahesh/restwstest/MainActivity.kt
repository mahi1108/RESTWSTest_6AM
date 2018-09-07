package cubex.mahesh.restwstest

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import cubex.mahesh.restwstest.beans.TrainStatusBean
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        date_btn.setOnClickListener {
            var cal = Calendar.getInstance()
/*   Context context, DatePickerDialog.OnDateSetListener listener, int year,
int month, int dayOfMonth          */
            var dpd = DatePickerDialog(this@MainActivity,
                    object :DatePickerDialog.OnDateSetListener{
                        override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                            et2.setText("$p3-${p2+1}-$p1")
                        }
                    },
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH))
            dpd.show()

        } // DPD

        get_status.setOnClickListener {

              var r = Retrofit.Builder().
                      baseUrl("https://api.railwayapi.com/").
                      addConverterFactory(GsonConverterFactory.create()). build()
            var api = r.create(RailwayAPI::class.java)
            var call:Call<TrainStatusBean> = api.getTrainStatus(
                    et1.text.toString(),et2.text.toString())
            call.enqueue(object : Callback<TrainStatusBean> {
                override fun onFailure(call: Call<TrainStatusBean>?, t: Throwable?) {

                }
                override fun onResponse(call: Call<TrainStatusBean>?,
                                                                response: Response<TrainStatusBean>?) {
                    var bean = response!!.body()
                    var list_stations = bean!!.route
                    var temp_list = mutableListOf<String>()

                    temp_list.add("Cur Sta Name :"+
                                            bean.currentStation.name)

                    for(item in list_stations!!){
                        if(item.hasArrived) {
                            temp_list.add(item.station.name + "\t" +
                                    item.hasArrived.toString() + "\n" +
                                    item.actarr+"\t       "+item.actdep)
                        }else{
                            temp_list.add(item.station.name + "\t" +
                                    item.hasArrived.toString() + "\n" +
                                    item.scharr+"\t       "+item.schdep)
                        }
                    }

                    var adapter = ArrayAdapter<String>(this@MainActivity,
                            android.R.layout.simple_list_item_single_choice,temp_list)
                    lview.adapter = adapter

                }
            })
        }
    } // onCreate
}
