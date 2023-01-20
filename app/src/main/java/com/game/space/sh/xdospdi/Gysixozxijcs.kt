package com.game.space.sh.xdospdi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import com.game.space.sh.databinding.ActivityGysixozxijcsBinding
import com.game.space.sh.kcixuhuvd.Isiojxjizucsgyaw
import kotlin.random.Random

class Gysixozxijcs : AppCompatActivity() {
    private lateinit var ofdijdij : ActivityGysixozxijcsBinding
    private var oskadisj: CountDownTimer? = null
    val zokzxjiz = listOf(
        "Bonus score : x4",
        "Bonus score : x6",
        "Bonus score : x8",
        "Bonus score : x12",
        "Bonus score : x15",
        "Bonus score : x20",
        "Bonus score : x30",
        "Bonus score : x50",
        "Bonus score : x70",
        "Bonus score : x100",
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ofdijdij = ActivityGysixozxijcsBinding.inflate(layoutInflater)
        setContentView(ofdijdij.root)
        ofdijdij.uuguhgfu.setOnClickListener {
            oitthuuhf()
        }
    }


    private fun oitthuuhf() = with(ofdijdij){
        var doifjisfd = Random.nextInt(20) + 10
        doifjisfd = doifjisfd * 36
        var aoxpxzkocox = 0
        oskadisj = object : CountDownTimer((doifjisfd * 20).toLong(), 1) {
            override fun onTick(millisUntilFinished: Long) {
               uuguhgfu.isClickable = false
                uuguhgfu.alpha = 0.7f
                aoxpxzkocox ++
                val losapdpsapsda = imageView.rotation + 2
                imageView.rotation = losapdpsapsda

                val losapdpsapsdas = imageView2.rotation + 2
                imageView2.rotation = losapdpsapsdas

                val losapdpsapsdasa = imageView3.rotation + 2
                imageView3.rotation = losapdpsapsdasa
                val losapdpsapsdasas = imageView4.rotation + 2
                imageView4.rotation = losapdpsapsdasas
                if(aoxpxzkocox>9) aoxpxzkocox = 0
                xozijzxicj.text = zokzxjiz[aoxpxzkocox]

            }

            override fun onFinish() {
                uuguhgfu.isClickable = true
                uuguhgfu.alpha = 1.0f
                xozijzxicj.text = zokzxjiz[Random.nextInt(10)]
                Handler().postDelayed({
                    startActivity(Intent(this@Gysixozxijcs,Isiojxjizucsgyaw::class.java))
                },1000)
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        oskadisj?.cancel()
    }
}