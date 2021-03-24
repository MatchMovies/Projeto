package com.br.matchmovies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.br.matchmovies.R
import com.br.matchmovies.fragments.HomeFragment
import com.br.matchmovies.fragments.MatchFragment
import com.br.matchmovies.fragments.MovieFragment
import com.br.matchmovies.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
      
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navegation)

        makeCurrentFragment(HomeFragment())
        bottomNavigationView.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.ic_home_nav_bar -> makeCurrentFragment(HomeFragment())
                R.id.ic_match_nav_bar -> makeCurrentFragment(MatchFragment())
                R.id.ic_movie_nav_bar -> makeCurrentFragment(MovieFragment())
                R.id.ic_profile_nav_bar -> makeCurrentFragment(ProfileFragment())
            }
            true
        }

        }


    private fun makeCurrentFragment(fragment: Fragment) =
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_wrapper, fragment)
                commit()
            }  


}
