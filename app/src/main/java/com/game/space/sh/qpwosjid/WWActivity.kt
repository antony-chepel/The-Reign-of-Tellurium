package com.game.space.sh.qpwosjid

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.provider.MediaStore
import android.util.Log
import android.webkit.*
import android.widget.Toast
import com.appsflyer.AppsFlyerLib
import com.game.space.sh.R
import com.game.space.sh.databinding.ActivityHuewweassdasBinding
import com.game.space.sh.kcixuhuvd.ConstanceAppClass
import com.game.space.sh.kcixuhuvd.ConstanceAppClass.instId
import com.game.space.sh.kcixuhuvd.ConstanceAppClass.myId

import com.game.space.sh.qpwosjid.ConstansUsage.fififififififififif
import com.game.space.sh.qpwosjid.ConstansUsage.fofofofofofofofofo
import com.game.space.sh.qpwosjid.ConstansUsage.kiokjjlikjhmkij
import com.game.space.sh.qpwosjid.ConstansUsage.lololololololo

import com.game.space.sh.qpwosjid.ConstansUsage.one
import com.game.space.sh.qpwosjid.ConstansUsage.pack
import com.game.space.sh.qpwosjid.ConstansUsage.sisisisifsisis
import com.game.space.sh.qpwosjid.ConstansUsage.subOne
import com.game.space.sh.qpwosjid.ConstansUsage.thrhtrhtrhtrht
import com.game.space.sh.qpwosjid.ConstansUsage.trololo
import com.google.android.material.snackbar.Snackbar
import com.onesignal.OneSignal
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.io.IOException

class WWActivity : AppCompatActivity() {
    private val ofjpeorjfperjg = 1
    var urlfififif = ""
    private var exitexitexitexit = false
    var hfgjrtgjhkh: ValueCallback<Array<Uri>>? = null
    var kgjfhdkxf: String? = null
    lateinit var jgidhgjdk: WebView
    lateinit var hrfghrdssxc: ActivityHuewweassdasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hrfghrdssxc = ActivityHuewweassdasBinding.inflate(layoutInflater)
        setContentView(hrfghrdssxc.root)

        jgidhgjdk = hrfghrdssxc.web
        Snackbar.make(
            hrfghrdssxc.root, "Loading...",
            Snackbar.LENGTH_LONG
        ).show()

        val cmngcmng = CookieManager.getInstance()
        cmngcmng.setAcceptCookie(true)
        cmngcmng.setAcceptThirdPartyCookies(jgidhgjdk, true)
        webSettings()
        webviewClient()
        webchrome()

    }

     private fun webviewClient(){
         val activity: Activity = this
         jgidhgjdk.webViewClient = object : WebViewClient() {
             override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                 try {
                     if (URLUtil.isNetworkUrl(url)) {
                         return false
                     }
                     if (appInstalledOrNot(url)) {

                         val intent = Intent(Intent.ACTION_VIEW)
                         intent.data = Uri.parse(url)
                         startActivity(intent)
                     } else {

                         Toast.makeText(
                             applicationContext,
                             "Application is not installed",
                             Toast.LENGTH_LONG
                         ).show()
                         startActivity(
                             Intent(
                                 Intent.ACTION_VIEW,
                                 Uri.parse("https://play.google.com/store/apps/details?id=org.telegram.messenger")
                             )
                         )
                     }
                     return true
                 } catch (e: Exception) {
                     return false
                 }
                 view.loadUrl(url)
             }


             override fun onPageFinished(view: WebView, url: String) {
                 super.onPageFinished(view, url)

                 saveUrl(url)
             }

             override fun onReceivedError(
                 view: WebView,
                 errorCode: Int,
                 description: String,
                 failingUrl: String
             ) {
                 Toast.makeText(activity, description, Toast.LENGTH_SHORT).show()
             }

         }

     }


    private fun webchrome(){
        jgidhgjdk.webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(
                webView: WebView, filePathCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: FileChooserParams
            ): Boolean {
                hfgjrtgjhkh?.onReceiveValue(null)
                hfgjrtgjhkh = filePathCallback
                var takePictureIntent: Intent? = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (takePictureIntent!!.resolveActivity(packageManager) != null) {

                    var photoFile: File? = null
                    try {
                        photoFile = createImageFile()
                        takePictureIntent.putExtra("PhotoPath", kgjfhdkxf)
                    } catch (ex: IOException) {

                    }


                    if (photoFile != null) {
                        kgjfhdkxf = "file:" + photoFile.absolutePath
                        takePictureIntent.putExtra(
                            MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(photoFile)
                        )
                    } else {
                        takePictureIntent = null
                    }
                }
                val contentSelectionIntent = Intent(Intent.ACTION_GET_CONTENT)
                contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE)
                contentSelectionIntent.type = "image/*"
                val intentArray: Array<Intent?> =
                    takePictureIntent?.let { arrayOf(it) } ?: arrayOfNulls(0)
                val chooserIntent = Intent(Intent.ACTION_CHOOSER)
                chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent)
                chooserIntent.putExtra(Intent.EXTRA_TITLE, getString(R.string.image_chooser))
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray)
                startActivityForResult(
                    chooserIntent, ofjpeorjfperjg
                )
                return true
            }

            @Throws(IOException::class)
            private fun createImageFile(): File {
                var imageStorageDir = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    "DirectoryNameHere"
                )
                if (!imageStorageDir.exists()) {
                    imageStorageDir.mkdirs()
                }


                imageStorageDir =
                    File(imageStorageDir.toString() + File.separator + "IMG_" + System.currentTimeMillis() + ".jpg")
                return imageStorageDir
            }

        }

        jgidhgjdk.loadUrl(urururururururur())

    }


    private fun pushToOneSignal(string: String) {
        OneSignal.setExternalUserId(
            string,
            object : OneSignal.OSExternalUserIdUpdateCompletionHandler {
                override fun onSuccess(results: JSONObject) {
                    try {
                        if (results.has("push") && results.getJSONObject("push").has("success")) {
                            val isPushSuccess = results.getJSONObject("push").getBoolean("success")
                            OneSignal.onesignalLog(
                                OneSignal.LOG_LEVEL.VERBOSE,
                                "Set external user id for push status: $isPushSuccess"
                            )
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    try {
                        if (results.has("email") && results.getJSONObject("email").has("success")) {
                            val isEmailSuccess =
                                results.getJSONObject("email").getBoolean("success")
                            OneSignal.onesignalLog(
                                OneSignal.LOG_LEVEL.VERBOSE,
                                "Set external user id for email status: $isEmailSuccess"
                            )
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    try {
                        if (results.has("sms") && results.getJSONObject("sms").has("success")) {
                            val isSmsSuccess = results.getJSONObject("sms").getBoolean("success")
                            OneSignal.onesignalLog(
                                OneSignal.LOG_LEVEL.VERBOSE,
                                "Set external user id for sms status: $isSmsSuccess"
                            )
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

                override fun onFailure(error: OneSignal.ExternalIdError) {
                    OneSignal.onesignalLog(
                        OneSignal.LOG_LEVEL.VERBOSE,
                        "Set external user id done with error: $error"
                    )
                }
            })
    }

    private fun webSettings() {
        val wstwstwstwst = jgidhgjdk.settings
        wstwstwstwst.javaScriptEnabled = true

        wstwstwstwst.useWideViewPort = true

        wstwstwstwst.loadWithOverviewMode = true
        wstwstwstwst.allowFileAccess = true
        wstwstwstwst.domStorageEnabled = true
        wstwstwstwst.userAgentString = wstwstwstwst.userAgentString.replace("; wv", "")

        wstwstwstwst.javaScriptCanOpenWindowsAutomatically = true
        wstwstwstwst.setSupportMultipleWindows(false)

        wstwstwstwst.displayZoomControls = false
        wstwstwstwst.builtInZoomControls = true
        wstwstwstwst.setSupportZoom(true)

        wstwstwstwst.pluginState = WebSettings.PluginState.ON
        wstwstwstwst.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        wstwstwstwst.setAppCacheEnabled(true)

        wstwstwstwst.allowContentAccess = true
    }

    private fun urururururururur(): String {

        val spoon = getSharedPreferences("SHARED_PREF", AppCompatActivity.MODE_PRIVATE)
        val intent = intent
        val str = intent.getStringExtra("WebInt")
       val sharedlink = getSharedPreferences(ConstanceAppClass.link, MODE_PRIVATE)
        val sharedMainId = getSharedPreferences(ConstanceAppClass.MAIN_ID, MODE_PRIVATE)
       val  sharedDeep= getSharedPreferences(ConstanceAppClass.DEEPL, MODE_PRIVATE)
        val sharedNaming= getSharedPreferences(ConstanceAppClass.C1, MODE_PRIVATE)
        val afId = AppsFlyerLib.getInstance().getAppsFlyerUID(this)
        val cpOne:String? = sharedNaming.getString(ConstanceAppClass.C1,"null")
        val mainId: String? = sharedMainId.getString(ConstanceAppClass.MAIN_ID,"null")
        val dpOne: String? = sharedDeep.getString(ConstanceAppClass.DEEPL,"null")
        val linkAB = sharedlink.getString(ConstanceAppClass.link,"null")

        val myTrId = spoon.getString(myId, null)
        val myInstId: String? = spoon.getString(instId, null)

        AppsFlyerLib.getInstance().setCollectAndroidID(true)

        var aft = ""

        when (str) {
            "MT" -> {
                aft =
                    "$linkAB$one$myTrId&$thrhtrhtrhtrht$myInstId&$fofofofofofofofofo$pack&$fififififififififif$kiokjjlikjhmkij&$sisisisifsisis$lololololololo"
                pushToOneSignal(myTrId.toString())
                Log.d("TESTAG", "urururururururur tracker: $aft")
            }
            "deepLink" -> {
                aft =
                    "$linkAB$subOne$dpOne&$one$afId&$thrhtrhtrhtrht$mainId&$fofofofofofofofofo$pack&$fififififififififif$kiokjjlikjhmkij&$sisisisifsisis$trololo"
                pushToOneSignal(myTrId.toString())
                Log.d("TESTAG", "urururururururur deep: $aft")
            }
            "campaign" -> {
                aft =
                    "$linkAB$subOne$cpOne&$one$afId&$thrhtrhtrhtrht$mainId&$fofofofofofofofofo$pack&$fififififififififif$kiokjjlikjhmkij&$sisisisifsisis$lololololololo"
                pushToOneSignal(afId.toString())
                Log.d("TESTAG", "urururururururur apps: $aft")
            }
        }

        Log.d("TESTAG", "Test Result $aft")
        return spoon.getString("SAVED_URL", aft).toString()
    }


    private fun appInstalledOrNot(uri: String): Boolean {

        val pm = packageManager
        try {

            pm.getPackageInfo("org.telegram.messenger", PackageManager.GET_ACTIVITIES)


            return true
        } catch (e: PackageManager.NameNotFoundException) {

        }
        return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode != ofjpeorjfperjg || hfgjrtgjhkh == null) {
            super.onActivityResult(requestCode, resultCode, data)
            return
        }
        var results: Array<Uri>? = null

        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (data == null || data.data == null) {
                results = arrayOf(Uri.parse(kgjfhdkxf))
            } else {
                val dataString = data.dataString
                if (dataString != null) {
                    results = arrayOf(Uri.parse(dataString))
                }
            }
        }
        hfgjrtgjhkh?.onReceiveValue(results)
        hfgjrtgjhkh = null
    }



    override fun onBackPressed() {

        if (jgidhgjdk.canGoBack()) {
            if (exitexitexitexit) {
                jgidhgjdk.stopLoading()
                jgidhgjdk.loadUrl(urlfififif)
            }
            this.exitexitexitexit = true
            jgidhgjdk.goBack()
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                exitexitexitexit = false
            }, 2000)

        } else {
            super.onBackPressed()
        }
    }


    fun saveUrl(lurlurlurlurlur: String?) {
        if (!lurlurlurlurlur!!.contains("t.me")) {
            if (urlfififif == "") {
                urlfififif = getSharedPreferences(
                    "SHARED_PREF",
                    AppCompatActivity.MODE_PRIVATE
                ).getString(
                    "SAVED_URL",
                    lurlurlurlurlur
                ).toString()

                val spspspspsppspspsp = getSharedPreferences("SHARED_PREF", AppCompatActivity.MODE_PRIVATE)
                val ededededededed = spspspspsppspspsp.edit()
                ededededededed.putString("SAVED_URL", lurlurlurlurlur)
                ededededededed.apply()
            }
        }
    }

}