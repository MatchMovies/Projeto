@file:Suppress("UNREACHABLE_CODE")

package com.br.matchmovies.fragments

import android.app.ProgressDialog.show
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
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.br.matchmovies.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileFragment : Fragment() {

    private lateinit var ib_expandable1: ImageButton
    private lateinit var expandableLayout: CardView
    private lateinit var ib_expandable2: ImageButton
    private lateinit var expandableLayout2: CardView
    private lateinit var btnsair: Button
    private lateinit var btnvoltar: Button

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)



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
        }

     //   btnvoltar.setOnClickListener {
     //       onBackPressed()}

    }
}
