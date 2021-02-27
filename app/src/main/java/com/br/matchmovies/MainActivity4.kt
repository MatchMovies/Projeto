package com.br.matchmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_match)

        initViews()
    }

    private fun initViews() {
        val mvoltar = findViewById<ImageView>(R.id.mvoltar)

        mvoltar.setOnClickListener {

            onBackPressed()
        }
    }
}