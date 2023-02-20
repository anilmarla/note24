package com.anil.notes24.ui.home.splashScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anil.notes24.R
import com.anil.notes24.ui.home.splashScreen.ui.main.SplashFragment

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SplashFragment.newInstance())
                .commitNow()
        }
    }
}