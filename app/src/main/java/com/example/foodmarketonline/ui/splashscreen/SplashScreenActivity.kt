package com.example.foodmarketonline.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.foodmarketonline.R
import com.example.foodmarketonline.ui.auth.AuthActivity

@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        Handler().postDelayed({
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }, 3000)

    }
}