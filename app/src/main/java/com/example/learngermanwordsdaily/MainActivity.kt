package com.example.learngermanwordsdaily

import GifDrawableImageViewTarget
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_main)

        var veri=Veri()

        var sharedPreferences = this.getSharedPreferences(packageName, android.content.Context.MODE_PRIVATE)

        var formatter = SimpleDateFormat("dd/MM/yyyy")


        var installday =formatter.parse(formatter.format( Date()))

        var showDate = sharedPreferences.getString("DATE","none")


        var str1=installday.toString()
        var str2=showDate.toString()

        if(str1.equals(str2)){
            loop=0
            txtBound.text="0"

        }
        else{
            loop=10
            txtBound.text="10"
        }
        btnGo.setOnClickListener {

            if(loop>0){
                btnGo.isClickable=false
                Glide.with(this).load(R.drawable.giphy).into(GifDrawableImageViewTarget(imgWheel, 1))
                loop=loop-1

                txtBound.text=loop.toString()
                ran = Random.nextInt(0, 100)


                Handler().postDelayed({
                    txtShow.text = veri.words[ran].toString()
                    btnGo.isClickable=true


                }, 4000)

                if(loop==0){
                    Toast.makeText(applicationContext,"You have reached your daily limit!",Toast.LENGTH_LONG).show()
                    sharedPreferences.edit().putString("DATE",installday.toString()).apply()
                }

            }

        }

        btnGoWord.setOnClickListener {
            var intent= Intent(this,WordActivity::class.java)
            intent.putExtra("deger",ran.toString())
            startActivity(intent)
        }

    }

}



