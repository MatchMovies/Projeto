package com.br.matchmovies.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment



import com.br.matchmovies.R

class ProfileFragment : Fragment() {


    lateinit var constraintViewMoreInfo: ConstraintLayout
    lateinit var constraintViewCard: ConstraintLayout
    lateinit var buttonExpandView: ImageButton
    var viewVisible: Boolean = false

    lateinit var constraintViewMoreInfoMm: ConstraintLayout
    lateinit var constraintViewCard2: ConstraintLayout
    lateinit var buttonExpandView2: ImageButton
    var viewVisibleMm: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewFragment = inflater.inflate(R.layout.fragment_profile, container, false)
        val viewFragmentMn = inflater.inflate(R.layout.fragment_profile, container, false)
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)
        val btnBack = view.findViewById<View>(R.id.btn_voltar) as Button
        val btnEditProfile = view.findViewById<View>(R.id.btn_editarPerfil) as Button
        val btnSair = view.findViewById<View>(R.id.ib_exit) as ImageButton

        btnBack.setOnClickListener {
            val transition = requireActivity().supportFragmentManager.beginTransaction()
            transition.replace(R.id.fl_wrapper, HomeFragment())
            transition.addToBackStack(null)
            transition.commit()
        }
        btnEditProfile.setOnClickListener {
            val transition = requireActivity().supportFragmentManager.beginTransaction()
            transition.replace(R.id.fl_wrapper, HomeFragment())
            transition.addToBackStack(null)
            transition.commit()
        }
        btnSair.setOnClickListener {
            this.requireActivity().finish()
        }

        initFields(viewFragment, viewFragmentMn)
        constraintViewCard.setOnClickListener {
            when(viewVisible ) {
                false -> expandCardview()
                true -> retractCardview()
            }
        }
        return viewFragment
    }

    private fun initFields(viewFragment: View, viewFragmentMn: View) {
        constraintViewMoreInfo = viewFragment.findViewById(R.id.constraintViewMoreInfo)
        constraintViewCard = viewFragment.findViewById(R.id.constraintViewCard)
        buttonExpandView = viewFragment.findViewById(R.id.buttonExpand)
    }

    private fun retractCardview() {
        viewVisible = false
        constraintViewMoreInfo.visibility = View.GONE
        buttonExpandView.setBackgroundResource(R.drawable.ic_baseline_expand_more_24)
    }


    private fun expandCardview() {
        viewVisible = true
        constraintViewMoreInfo.visibility = View.VISIBLE
        buttonExpandView.setBackgroundResource(R.drawable.ic_baseline_expand_less_24)
    }


}
