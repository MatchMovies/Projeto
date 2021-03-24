@file:Suppress("UNREACHABLE_CODE")

package com.br.matchmovies.fragments


import android.os.Build
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView

import com.br.matchmovies.R


class ProfileFragment : Fragment() {

    private lateinit var ib_expandable1: ImageButton
    private lateinit var expandableLayout: CardView
    private lateinit var ib_expandable2: ImageButton
    private lateinit var expandableLayout2: CardView


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_profile, container, false)
        val btnBack = view.findViewById<View>(R.id.btnvoltar) as Button

        btnBack.setOnClickListener {

            val transition = requireActivity().supportFragmentManager.beginTransaction()
            transition.replace(R.id.fl_wrapper, HomeFragment())
            transition.addToBackStack(null)
            transition.commit()
        }

  /*
        ib_expandable1.setOnClickListener{
            if (expandableLayout.visibility == View.GONE){
                TransitionManager.beginDelayedTransition(expandableLayout, AutoTransition() )
                expandableLayout.visibility = View.VISIBLE
            } else {
                TransitionManager.beginDelayedTransition(expandableLayout, AutoTransition() )
                expandableLayout.visibility = View.GONE
            }
        }
        ib_expandable2.setOnClickListener{
            if (expandableLayout2.visibility == View.GONE){
                TransitionManager.beginDelayedTransition(expandableLayout2, AutoTransition() )
                expandableLayout2.visibility = View.VISIBLE
            } else {
                TransitionManager.beginDelayedTransition(expandableLayout2, AutoTransition() )
                expandableLayout2.visibility = View.GONE
            }
        } */

        return view



     //   btnvoltar.setOnClickListener {
     //       onBackPressed()}

    }
}
