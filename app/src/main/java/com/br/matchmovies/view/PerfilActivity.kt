package com.br.matchmovies.view

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.br.matchmovies.R
import kotlin.system.exitProcess

class PerfilActivity : AppCompatActivity() {
    private val btn_dados_pessoais by lazy { findViewById<Button>(R.id.btn_dados_pessoais) }
    private val btn_sair by lazy { findViewById<Button>(R.id.btn_sair) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_perfil)

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_profile)
        val bitmapRound = RoundedBitmapDrawableFactory.create(resources, bitmap)
        bitmapRound.cornerRadius = 1000f


        btn_dados_pessoais.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)

         btn_sair.setOnClickListener { finish()
         System.out}
        }
    }
}
