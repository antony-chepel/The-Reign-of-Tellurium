package com.game.space.sh

import android.app.Application
import android.content.Context
import com.game.space.sh.kcixuhuvd.ConstanceAppClass.myId
import com.game.space.sh.kcixuhuvd.ConstanceAppClass.osNal
import com.game.space.sh.kcixuhuvd.ConstanceAppClass.instId
import com.my.tracker.MyTracker
import com.onesignal.OneSignal
import dagger.hilt.android.HiltAndroidApp
import java.util.*


@HiltAndroidApp
class InMainClass : Application() {
    override fun onCreate() {
        super.onCreate()
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(osNal)

        val oicjicjxd = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val tifjjdfijidf = getSharedPreferences("PREFS_NAME", 0)

        val oxkjicjxiz = MyTracker.getTrackerParams()
        val psosloxkczjixjiz = MyTracker.getTrackerConfig()
        val tiuhdsjifijsdfji = MyTracker.getInstanceId(this)
        psosloxkczjixjiz.isTrackingLaunchEnabled = true
        val siajddiasj = UUID.randomUUID().toString()

        if (tifjjdfijidf.getBoolean("my_first_time", true)) {
            oxkjicjxiz.setCustomUserId(siajddiasj)
            oicjicjxd.edit().putString(myId, siajddiasj).apply()
            oicjicjxd.edit().putString(instId, tiuhdsjifijsdfji).apply()
            tifjjdfijidf.edit().putBoolean("my_first_time", false).apply()
        } else {
            val mcxivjivjxcjixcv = oicjicjxd.getString(myId, siajddiasj)
            oxkjicjxiz.setCustomUserId(mcxivjivjxcjixcv)
        }
        MyTracker.initTracker("01725393035861516307", this)
    }
}
