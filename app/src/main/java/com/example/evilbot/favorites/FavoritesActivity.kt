package com.example.evilbot.favorites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.evilbot.R
import com.example.evilbot.favorites.ui.main.FavoritesFragment

class FavoritesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorites_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.favorites_container, FavoritesFragment.newInstance())
                .commitNow()
        }
    }
}