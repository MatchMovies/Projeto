package com.br.matchmovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_match)

        initViews()
    }

    private fun initViews() {

        val mexcluir = findViewById<ImageView>(R.id.mexcluir)

        mexcluir.setOnClickListener {
            val intent = Intent( this, MainActivity4::class.java)
            startActivity(intent)
        }

    }
}