package com.br.matchmovies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.br.matchmovies.R

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

       // setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        //supportActionBar?.setHomeAsUpIndicator(R.drawable.logo_toolbar)

    }
}