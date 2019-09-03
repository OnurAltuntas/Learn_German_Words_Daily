package com.example.learngermanwordsdaily

import GifDrawableImageViewTarget
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    val URL = "https://media.giphy.com/media/UUgNVN1kntCK54JOew/giphy.gif"
    var ran=0
    var loop=10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_main)
        var veri=Veri()


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
                }


                // btnGo.isClickable=true
            }



        }

        btnGoWord.setOnClickListener {
            var intent= Intent(this,WordActivity::class.java)
            intent.putExtra("deger",ran.toString())
            startActivity(intent)
        }


    }


}
