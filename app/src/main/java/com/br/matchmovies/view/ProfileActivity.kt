package com.br.matchmovies.view

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.br.matchmovies.R
import com.br.matchmovies.fragments.ProfileFragment

@Suppress("UNREACHABLE_CODE")
class ProfileActivity : AppCompatActivity() {

    var fragmentManager: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_profile)


        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fl_wrapper, ProfileFragment())
        fragmentTransaction.commit()
    }
}