package com.br.matchmovies.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.br.matchmovies.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login)

        initViews()
    }

    private fun initViews() {

        val btnEntrar = findViewById<Button>(R.id.btentrar)

        btnEntrar.setOnClickListener {

          val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }


    }


}
