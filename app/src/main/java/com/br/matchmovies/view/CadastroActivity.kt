package com.br.matchmovies.view

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.br.matchmovies.R
import com.br.matchmovies.databinding.ActivityMainCadastroBinding

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainCadastroBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        
    }
}