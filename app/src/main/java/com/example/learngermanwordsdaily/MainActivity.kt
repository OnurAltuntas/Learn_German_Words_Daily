package com.example.learngermanwordsdaily

import GifDrawableImageViewTarget
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    val URL = "https://media.giphy.com/media/UUgNVN1kntCK54JOew/giphy.gif"
    var ran=0
    var loop=10
    var currentdate=""


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_main)

        var sharedPreferences = this.getSharedPreferences(packageName, android.content.Context.MODE_PRIVATE)
        val url="https://api.timezonedb.com/v2.1/get-time-zone?key=EBPJG1SJ3JV5&format=json&by=zone&zone=America/Chicago"
        val dateobject=JsonObjectRequest(Request.Method.GET,url,null,object:Response.Listener<JSONObject>{
            override fun onResponse(response: JSONObject?) {
                var veri=Veri()
                var currentdate=response!!.getString("formatted")

                var string: String? = currentdate
                var yourArray: List<String> = string!!.split(" ")
                currentdate=yourArray[0]
                txttarih2.text=currentdate


//              var formatter = SimpleDateFormat("dd/MM/yyyy")

                var installday =currentdate.toString()

                var showDate = sharedPreferences.getString("DATE","none")

                var str1=installday.toString()
                var str2=showDate.toString()

                txttarih.text=str1

                if(str1.equals(str2)){
                    loop=sharedPreferences.getString("loop","none")!!.toInt()
                    txtBound.text=loop.toString()

                }
                else{
                    loop=10
                    txtBound.text="10"
                }

                btnGo.setOnClickListener {

                    if(loop>0){
                        btnGo.isClickable=false
                        Glide.with(this@MainActivity).load(R.drawable.giphy).into(GifDrawableImageViewTarget(imgWheel, 1))
                        loop=loop-1
                        sharedPreferences.edit().putString("loop",loop.toString()).apply()
                        sharedPreferences.edit().putString("DATE",currentdate).apply()

                        txtBound.text=loop.toString()
                        ran = Random.nextInt(0, 100)

                        Handler().postDelayed({
                            txtShow.text = veri.words[ran].toString()
                            btnGo.isClickable=true


                        }, 4000)

                        if(loop==0){
                            Toast.makeText(applicationContext,"You have reached your daily limit!",Toast.LENGTH_LONG).show()
                            sharedPreferences.edit().putString("DATE",currentdate).apply()
//                    txttarih.text=currentdate+"boşluk"
                        }

                    }

                }
            }

        },object:Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?) {
                Toast.makeText(this@MainActivity,"Url Hatasi ",Toast.LENGTH_LONG).show()
            }

        })

        MySingleton.getInstance(this)?.addToRequestQueue(dateobject)


        btnGoWord.setOnClickListener {
            var intent= Intent(this,WordActivity::class.java)
            intent.putExtra("deger",ran.toString())
            startActivity(intent)
        }

    }

}



