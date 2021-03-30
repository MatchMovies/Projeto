package com.br.matchmovies.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton

import com.br.matchmovies.R


class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_profile, container, false)
        val btnBack = view.findViewById<View>(R.id.btnvoltar) as Button
        val btnSair = view.findViewById<View>(R.id.ib_sair) as ImageButton
/*
        val btnExp1 = view.findViewById<View>(R.id.ib_expandable1) as ImageButton
        val btnExp2 = view.findViewById<View>(R.id.ib_expandable2) as ImageButton
        val btnExp3 = view.findViewById<View>(R.id.ib_expandable3) as ImageButton
        val expLayout1 = view.findViewById<View>(R.id.cardViewdp) as CardView
        val expLayout2 = view.findViewById<View>(R.id.cardViewmm) as CardView
        val expLayout3 = view.findViewById<View>(R.id.cardViewgc) as CardView*/


        btnBack.setOnClickListener {

            val transition = requireActivity().supportFragmentManager.beginTransaction()
            transition.replace(R.id.fl_wrapper, HomeFragment())
            transition.addToBackStack(null)
            transition.commit()
        }

        btnSair.setOnClickListener {
        val manager = requireActivity().supportFragmentManager
        manager.beginTransaction().remove(this).commit()
        }
/*

        btnExp1.setOnClickListener{
            if (expLayout1.visibility == View.GONE){
            TransitionManager.beginDelayedTransition(expLayout1, AutoTransition() )
            expLayout1.visibility = View.VISIBLE
        } else {
            TransitionManager.beginDelayedTransition(expLayout2, AutoTransition() )
            expLayout2.visibility = View.GONE
          }
        }
        btnExp2.setOnClickListener{
            if (expLayout2.visibility == View.GONE){
                TransitionManager.beginDelayedTransition(expLayout2, AutoTransition() )
                expLayout2.visibility = View.VISIBLE
            } else {
                TransitionManager.beginDelayedTransition(expLayout2, AutoTransition() )
                expLayout2.visibility = View.GONE
            }
        }
        btnExp3.setOnClickListener{
            if (expLayout3.visibility == View.GONE){
                TransitionManager.beginDelayedTransition(expLayout3, AutoTransition() )
                expLayout3.visibility = View.VISIBLE
            } else {
                TransitionManager.beginDelayedTransition(expLayout3, AutoTransition() )
                expLayout3.visibility = View.GONE
            }
        }*/



        return view
    }
}



