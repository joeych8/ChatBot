package com.example.evilbot.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.evilbot.R
import com.example.evilbot.chat.ChatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportFragmentManager.beginTransaction()
            .replace(R.id.login_container, LoginFragment()).commitNow()

    }

    fun goToTwistFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.login_container, TwistFragment())
        fragmentTransaction.commit()
    }

    fun goToChatFragment() {
        val intent = Intent(this, ChatActivity::class.java)
        intent.flags = intent.flags or Intent.FLAG_ACTIVITY_NO_HISTORY
        startActivity(intent)
    }
}