package app.binar.synrgy.android.helloworld.ui.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import app.binar.synrgy.android.helloworld.R
import app.binar.synrgy.android.helloworld.constant.Const
import app.binar.synrgy.android.helloworld.ui.getStarted.GetStartedActivity
import app.binar.synrgy.android.helloworld.ui.homePage.HomeActivity
import app.binar.synrgy.android.helloworld.ui.homenavigation.HomeNavigationActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences: SharedPreferences =
            applicationContext.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE)

        //memberikan delay sebelum elsekusi
        Handler(Looper.getMainLooper()).postDelayed({
            //perintah untuk pindah halaman ke getStarted
//            startActivity(Intent(this,GetStartedActivity::class.java))
//            finish() // biar diback gk balik

            //kondisi untuk mengecheck flag is login or not
            if (sharedPreferences.getBoolean(Const.IS_LOGIN, false)) {
                // kondisi nilai login true
                startActivity(Intent(this, HomeNavigationActivity::class.java))
                finish()
            } else {
                // kondisi nilai login false
                startActivity(Intent(this, GetStartedActivity::class.java))
                finish()
            }

        }, 3000)

    }
}