package com.br.matchmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

       // setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        //supportActionBar?.setHomeAsUpIndicator(R.drawable.logo_toolbar)

    }
}