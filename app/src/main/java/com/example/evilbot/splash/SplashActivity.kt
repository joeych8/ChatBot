package com.example.evilbot.splash

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.evilbot.R
import com.example.evilbot.SHARED_PREFS_NAME
import com.example.evilbot.chat.ChatActivity
import com.example.evilbot.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT = 2000L //Duration on splashActivity (2 sec)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)



        Handler().postDelayed({

            val sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

            val activityIntent = if (sharedPreferences.getString(SHARED_PREFS_NAME, null) != null) {
                Intent(this, ChatActivity::class.java)
            } else {
                Intent(this, LoginActivity::class.java)
            }
            activityIntent.flags = activityIntent.flags or Intent.FLAG_ACTIVITY_NO_HISTORY
            startActivity(activityIntent)
            finish()
        }, SPLASH_TIME_OUT)
    }
}