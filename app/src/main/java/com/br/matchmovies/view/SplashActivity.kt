package com.br.matchmovies.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.br.matchmovies.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadSplash()
    }

    private fun loadSplash(){
        Handler().postDelayed({
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        },2000)
    }




}