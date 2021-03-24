package com.br.matchmovies.view
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

import com.br.matchmovies.R

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


    @SuppressLint("WrongViewCast")
    private fun initViews() {

        val btnEnter = findViewById<Button>(R.id.btentrar)
        btnEnter.setOnClickListener {
          val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }


        val btnCadastro = findViewById<TextView>(R.id.btcadastrar)
        btnCadastro.setOnClickListener{
          startActivity(Intent(this, CadastroActivity::class.java))
        }

        val btnEsqueciSenha = findViewById<TextView>(R.id.btesqueci)
        btnEsqueciSenha.setOnClickListener{
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

    }


}
