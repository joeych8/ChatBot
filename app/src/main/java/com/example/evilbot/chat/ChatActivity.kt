package com.example.evilbot.chat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.evilbot.R
import com.example.evilbot.SHARED_PREFS_NAME
import com.example.evilbot.favorites.ui.main.FavoritesFragment
import com.example.evilbot.login.LoginActivity


class ChatActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_activity)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.chat_container, ChatFragment())
                .commitNow()
        }
    }

    fun goToFavorites() {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.chat_container, FavoritesFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun logUserOut() {

        val sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = intent.flags or Intent.FLAG_ACTIVITY_NO_HISTORY
        startActivity(intent)
    }
}