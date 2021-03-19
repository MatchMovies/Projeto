package com.br.matchmovies.view
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.br.matchmovies.R

class PerfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_perfil)

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_profile)
        val bitmapRound = RoundedBitmapDrawableFactory.create(resources, bitmap)
        bitmapRound.cornerRadius = 1000f
    }

}
