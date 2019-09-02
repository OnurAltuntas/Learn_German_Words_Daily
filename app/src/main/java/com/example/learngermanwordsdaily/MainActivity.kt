package com.example.learngermanwordsdaily

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random
import com.bumptech.glide.load.resource.gif.GifDrawable
import android.graphics.drawable.Drawable
import com.bumptech.glide.request.target.ImageViewTarget
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.ImageView
import androidx.annotation.Nullable
import GifDrawableImageViewTarget
import android.content.Intent
import android.os.Handler



class MainActivity : AppCompatActivity() {

    val URL = "https://media.giphy.com/media/UUgNVN1kntCK54JOew/giphy.gif"
    var ran=0
    var loop=10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var veri=Veri()

        btnGo.setOnClickListener {
            if(loop>0){
                Glide.with(this).load(R.drawable.giphy).into(GifDrawableImageViewTarget(imgWheel, 1))
                loop=loop-1

                txtBound.text=loop.toString()
                ran = Random.nextInt(0, 100)


                Handler().postDelayed({
                    txtShow.text = veri.words[ran].toString()

                    //btnGo.isClickable=false

                }, 3300)
                if(loop==0){
                    Toast.makeText(applicationContext,"You have reached your daily limit!",Toast.LENGTH_LONG).show()
                }


                // btnGo.isClickable=true
            }



        }

        btnGoWord.setOnClickListener {
            var intent= Intent(this,WordActivity::class.java)
            intent.putExtra("deger",""+ran)
            startActivity(intent)
        }


    }


}
