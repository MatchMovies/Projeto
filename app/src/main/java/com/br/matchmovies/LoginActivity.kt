package com.br.matchmovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login)

        initViews()
    }

    private fun initViews() {

        val botao = findViewById<Button>(R.id.btentrar)

        botao.setOnClickListener {

          val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }


    }


}
