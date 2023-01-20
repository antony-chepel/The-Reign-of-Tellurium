package com.game.space.sh.kcixuhuvd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.game.space.sh.R
import com.game.space.sh.databinding.ActivityIsiojxjizucsgyawBinding
import com.game.space.sh.xdospdi.Gysixozxijcs
import kotlin.random.Random

class Isiojxjizucsgyaw : AppCompatActivity() {
    private lateinit var ocijjixc : ActivityIsiojxjizucsgyawBinding
    val lisdysgdsyg = listOf(
        R.drawable.reft_1,
        R.drawable.reft_2,
        R.drawable.reft_3,
        R.drawable.reft_4,
    )
    val isuayggydwgy = listOf(
        "You got 200 magic points",
        "You got 300 magic points",
        "You got 500 magic points",
        "You got 800 magic points",
        "You got 1200 magic points",
        "You got 1500 magic points",
        "You got 1800 magic points",
        "You got 2000 magic points",
        "You got 2500 magic points",
        "You got 3000 magic points",
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ocijjixc = ActivityIsiojxjizucsgyawBinding.inflate(layoutInflater)
        setContentView(ocijjixc.root)
        ixzuhcsy()
    }

    private fun ixzuhcsy() = with(ocijjixc){
        imageView5.setImageResource(lisdysgdsyg[Random.nextInt(4)])
        tvoicj.text = isuayggydwgy[Random.nextInt(10)]
        button2.setOnClickListener {
            startActivity(Intent(this@Isiojxjizucsgyaw,Gysixozxijcs::class.java))
        }

    }
}