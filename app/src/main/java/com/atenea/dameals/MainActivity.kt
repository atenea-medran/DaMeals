package com.atenea.dameals

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.atenea.dameals.databinding.ActivityMainBinding
import com.atenea.dameals.presentation.favoriteList.FavoriteMealListScreen


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        with(binding) {
            setContentView(root)
            setSupportActionBar(toolbar)
            bottomNavigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> {
                        val intent = Intent(this@MainActivity, MainActivity::class.java)
                        startActivity(intent)
                        return@setOnItemSelectedListener true
                    }
                    R.id.favorites -> {
                        val intent = Intent(this@MainActivity, FavoriteMealListScreen::class.java)
                        startActivity(intent)
                        return@setOnItemSelectedListener true

                    }
                }
                false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}