package com.example.moviecatalog.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalog.R
import com.example.moviecatalog.adapter.TrailerMovieAdapter
import com.example.moviecatalog.adapter.TrailerTvAdapter
import com.example.moviecatalog.viewmodel.DetailMovieViewModel
import com.example.moviecatalog.viewmodel.DetailTVViewModel
import com.example.moviecatalog.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_trailer.*

class TrailerFragment(private val trailerMovieON:Boolean?) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trailer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(activity != null){
            if(trailerMovieON != null){
                val factory = ViewModelFactory.getInstance(requireActivity(), requireActivity())
                val viewModel = ViewModelProvider(requireActivity(), factory)[DetailMovieViewModel::class.java]

                viewModel.getTrailer().observe(viewLifecycleOwner, {
                    trailer ->

                    rvTrailer.layoutManager = LinearLayoutManager(activity)
                    val trailerAdapter = TrailerMovieAdapter()
                    trailerAdapter.setData(trailer)
                    rvTrailer.adapter = trailerAdapter
                    rvTrailer.setHasFixedSize(true)
                })
            }else{
                val factory = ViewModelFactory.getInstance(requireActivity(), requireActivity())
                val viewModel = ViewModelProvider(requireActivity(), factory)[DetailTVViewModel::class.java]

                viewModel.getTrailer().observe(viewLifecycleOwner, {
                    trailer ->

                    rvTrailer.layoutManager = LinearLayoutManager(activity)
                    val trailerAdapter = TrailerTvAdapter()
                    trailerAdapter.setData(trailer)
                    rvTrailer.adapter = trailerAdapter
                    rvTrailer.setHasFixedSize(true)
                })
            }
        }
    }
}