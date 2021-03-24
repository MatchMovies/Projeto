package com.br.matchmovies.fragments

import android.os.Build
import android.os.Bundle
import android.system.Os.close
import android.system.Os.remove
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.lifecycle.Lifecycle
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager

import com.br.matchmovies.R
import kotlin.system.exitProcess


class ProfileFragment : Fragment() {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)
        val btnBack = view.findViewById<View>(R.id.btnvoltar) as Button
        val btnSair = view.findViewById<View>(R.id.ib_sair) as ImageButton

        val btnExp1 = view.findViewById<View>(R.id.ib_expandable1) as ImageButton
        val btnExp2 = view.findViewById<View>(R.id.ib_expandable2) as ImageButton
        val expLayout1 = view.findViewById<View>(R.id.cardViewdp) as CardView
        val expLayout2 = view.findViewById<View>(R.id.cardViewmm) as CardView

        btnBack.setOnClickListener {

            val transition = requireActivity().supportFragmentManager.beginTransaction()
            transition.replace(R.id.fl_wrapper, HomeFragment())
            transition.addToBackStack(null)
            transition.commit()
        }

        btnSair.setOnClickListener {
           this.requireActivity().finish()
        }

        btnExp1.setOnClickListener {

            if (expLayout1.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(expLayout1, AutoTransition())
                expLayout1.visibility = View.VISIBLE
            } else {
                TransitionManager.beginDelayedTransition(expLayout1)
                expLayout1.visibility = View.GONE
            }
        }
        btnExp2.setOnClickListener {
            if (expLayout2.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(expLayout2, AutoTransition())
                expLayout2.visibility = View.VISIBLE
            } else {
                TransitionManager.beginDelayedTransition(expLayout2, AutoTransition())
                expLayout2.visibility = View.GONE
            }
        }

        return view
    }
}





