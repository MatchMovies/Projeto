package com.br.matchmovies.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProviders
import com.br.matchmovies.R
import com.br.matchmovies.viewmodel.MatchMoviesViewModel
import com.br.matchmovies.viewmodel.SplashScreenViewModel

class SplashActivity : AppCompatActivity() {

    private lateinit var viewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
       // loadSplash()
        viewModel = ViewModelProviders.of(this).get(SplashScreenViewModel::class.java)
        viewModel.loading.observe(this){
               if(!it){
                   loadSplash()
               }
        }


    }

    private fun loadSplash(){
        Handler().postDelayed({
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        },2000)
    }






}