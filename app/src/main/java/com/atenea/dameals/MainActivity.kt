package com.atenea.dameals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.atenea.dameals.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        with(binding) {
            setContentView(root)
            setSupportActionBar(toolbar)
        }
    }
}