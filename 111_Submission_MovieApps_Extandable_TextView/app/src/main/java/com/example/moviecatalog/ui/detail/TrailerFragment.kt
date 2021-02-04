package com.example.moviecatalog.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalog.R
import com.example.moviecatalog.adapter.movie.TrailerMovieAdapter
import com.example.moviecatalog.adapter.tv.TrailerTvAdapter
import com.example.moviecatalog.data.source.vo.Status
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
                    if(trailer != null){
                        when(trailer.status){
                            Status.LOADING -> {}
                            Status.SUCCESS -> {
                                rvTrailer.layoutManager = LinearLayoutManager(activity)
                                val trailerAdapter = TrailerMovieAdapter()
                                if(trailer.data?.name != null && trailer.data.key != null){
                                    val name = trailer.data.name.split(",")
                                    val key = trailer.data.key.split(",")
                                    trailerAdapter.setData(name, key)
                                }
                                rvTrailer.adapter = trailerAdapter
                                rvTrailer.setHasFixedSize(true)
                            }
                            Status.ERROR -> {
                                Toast.makeText(context, getString(R.string.error), Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                })
            }else{
                val factory = ViewModelFactory.getInstance(requireActivity(), requireActivity())
                val viewModel = ViewModelProvider(requireActivity(), factory)[DetailTVViewModel::class.java]

                viewModel.getTrailer().observe(viewLifecycleOwner,{
                    trailer ->
                    if(trailer.data != null){
                        when(trailer.status){
                            Status.LOADING -> {}
                            Status.SUCCESS -> {
                                rvTrailer.layoutManager = LinearLayoutManager(activity)
                                val trailerAdapter = TrailerTvAdapter()
                                if(trailer.data.name != null && trailer.data.key != null){
                                    val name = trailer.data.name.split(",")
                                    val key = trailer.data.key.split(",")
                                    trailerAdapter.setData(name, key)
                                }
                                rvTrailer.adapter = trailerAdapter
                                rvTrailer.setHasFixedSize(true)
                            }
                            else -> {}
                        }
                    }
                })
            }
        }
    }
}