package com.br.matchmovies.fragments
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment



import com.br.matchmovies.R
import com.br.matchmovies.view.CadastroActivity
import com.br.matchmovies.view.MovieDetailsActivity
import java.io.Serializable

class ProfileFragment : Fragment() {


    lateinit var constraintViewMoreInfo: ConstraintLayout
    lateinit var constraintViewCard: ConstraintLayout
    lateinit var buttonExpandView: ImageButton
    var viewVisible: Boolean = false

    lateinit var constraintViewMoreInfoMm: ConstraintLayout
    lateinit var constraintViewCard2: ConstraintLayout
    lateinit var buttonExpandView2: ImageButton
    var viewVisibleMm: Boolean = false

    lateinit var constraintViewMoreInfoGc: ConstraintLayout
    lateinit var constraintViewCard3: ConstraintLayout
    lateinit var buttonExpandView3: ImageButton
    var viewVisibleGc: Boolean = false

    lateinit var constraintViewMoreInfoContato: ConstraintLayout
    lateinit var constraintViewCardContato: ConstraintLayout
    lateinit var buttonExpandView4: ImageButton
    var viewVisibleContato: Boolean = false

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val viewFragment = inflater.inflate(R.layout.fragment_profile, container, false)
        val viewFragmentMn = inflater.inflate(R.layout.fragment_profile, container, false)
        val viewFragmentGc = inflater.inflate(R.layout.fragment_profile, container, false)
        val viewFragmentContato = inflater.inflate(R.layout.fragment_profile, container, false)
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)
        //val btnBack = view.findViewById<View>(R.id.btn_voltar) as Button
/*        val btnEditProfile = view.findViewById<View>(R.id.btn_editarPerfil) as Button
        val btnSair = view.findViewById<View>(R.id.ib_exit) as ImageButton*/

        /*    btnBack.setOnClickListener {
                 val transition = requireActivity().supportFragmentManager.beginTransaction()
                 transition.replace(R.id.fl_wrapper, HomeFragment())
                 transition.addToBackStack(null)
                 transition.commit()
             }*/


        val btnEditProfile = view.findViewById<View>(R.id.btn_editarPerfil) as Button
        val btnSair = view.findViewById<View>(R.id.ib_exit) as ImageButton



        btnEditProfile.setOnClickListener {
            val intent = Intent(requireContext(), CadastroActivity::class.java)
            startActivity(intent)
        }

        btnSair.setOnClickListener {
            this.onDestroy()
        }

        initFields(viewFragment, viewFragmentMn, viewFragmentGc, viewFragmentContato)
        constraintViewCard.setOnClickListener {
            when(viewVisible ) {
                false -> expandCardview()
                true -> retractCardview()
            }
        }

        constraintViewCard2.setOnClickListener {
            when (viewVisibleMm) {
                false -> expandCardview2()
                true -> retractCardview2()
            }
        }
        constraintViewCard3.setOnClickListener {
            when (viewVisibleGc) {
                false -> expandCardview3()
                true -> retractCardview3()
            }
        }
        constraintViewCardContato.setOnClickListener {
            when (viewVisibleContato) {
                false -> expandCardview4()
                true -> retractCardview4()
            }
        }
        return viewFragment
    }

    private fun initFields(viewFragment: View, viewFragmentMn: View, viewFragmentGc: View, viewFragmentContato: View) {
        constraintViewMoreInfo = viewFragment.findViewById(R.id.constraintViewMoreInfo)
        constraintViewCard = viewFragment.findViewById(R.id.constraintViewCard)
        buttonExpandView = viewFragment.findViewById(R.id.buttonExpand)

        constraintViewMoreInfoMm = viewFragment.findViewById(R.id.constraintViewMoreInfoMm)
        constraintViewCard2 = viewFragment.findViewById(R.id.constraintViewCard2)
        buttonExpandView2 = viewFragment.findViewById(R.id.buttonExpand2)

        constraintViewMoreInfoGc = viewFragment.findViewById(R.id.constraintViewMoreInfoGc)
        constraintViewCard3 = viewFragment.findViewById(R.id.constraintViewCardgc)
        buttonExpandView3 = viewFragment.findViewById(R.id.buttonExpand3)

        constraintViewMoreInfoContato = viewFragment.findViewById(R.id.constraintViewMoreInfocontato)
        constraintViewCardContato = viewFragment.findViewById(R.id.constraintViewCardcontato)
        buttonExpandView4 = viewFragment.findViewById(R.id.buttonExpand4)


    }

    private fun retractCardview() {
        viewVisible = false
        constraintViewMoreInfo.visibility = View.GONE
        buttonExpandView.setBackgroundResource(R.drawable.ic_baseline_expand_more_24)
    }
    private fun retractCardview2(){
        viewVisibleMm = false
        constraintViewMoreInfoMm.visibility = View.GONE
        buttonExpandView2.setBackgroundResource(R.drawable.ic_baseline_expand_more_24)
    }
    private fun retractCardview3(){
        viewVisibleGc = false
        constraintViewMoreInfoGc.visibility = View.GONE
        buttonExpandView3.setBackgroundResource(R.drawable.ic_baseline_expand_more_24)
    }
    private fun retractCardview4() {
        viewVisibleContato = false
        constraintViewMoreInfoContato.visibility = View.GONE
        buttonExpandView4.setBackgroundResource(R.drawable.ic_baseline_expand_more_24)
    }


    private fun expandCardview() {
        viewVisible = true
        constraintViewMoreInfo.visibility = View.VISIBLE
        buttonExpandView.setBackgroundResource(R.drawable.ic_baseline_expand_less_24)
    }

    private fun expandCardview2(){
        viewVisibleMm = true
        constraintViewMoreInfoMm.visibility = View.VISIBLE
        buttonExpandView2.setBackgroundResource(R.drawable.ic_baseline_expand_less_24)
    }
    private fun expandCardview3() {
        viewVisibleGc = true
        constraintViewMoreInfoGc.visibility = View.VISIBLE
        buttonExpandView3.setBackgroundResource(R.drawable.ic_baseline_expand_less_24)

    }
    private fun expandCardview4() {
        viewVisibleContato = true
        constraintViewMoreInfoContato.visibility = View.VISIBLE
        buttonExpandView4.setBackgroundResource(R.drawable.ic_baseline_expand_less_24)
    }


}