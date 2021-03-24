package com.br.matchmovies.fragments



import android.os.Bundle
import android.service.autofill.FieldClassification
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.br.matchmovies.R



@Suppress("UNREACHABLE_CODE")
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        val btnMovie = view.findViewById<View>(R.id.button_ic_home_filme) as Button
        val btnSeries = view.findViewById<View>(R.id.button_ic_home_series) as Button

        btnMovie.setOnClickListener {


            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_wrapper, MatchFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        btnSeries.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_wrapper, MatchFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view
    }

}