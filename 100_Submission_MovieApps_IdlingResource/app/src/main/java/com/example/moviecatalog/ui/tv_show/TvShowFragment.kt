package com.example.moviecatalog.ui.tv_show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalog.R
import com.example.moviecatalog.adapter.TvHorizontalAdapter
import com.example.moviecatalog.adapter.TvShowAdapter
import com.example.moviecatalog.viewmodel.TvViewModel
import com.example.moviecatalog.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_film.*

class TvShowFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(activity != null){

            val factory = ViewModelFactory.getInstance(viewLifecycleOwner, requireContext())
            val viewModel = ViewModelProvider(this,factory)[TvViewModel::class.java]

            val tvHorizontalAdapter = TvHorizontalAdapter()

            val tvAdapter = TvShowAdapter()

            viewModel.getTvPop().observe(viewLifecycleOwner, {
                tvPopular ->
                progressBar.visibility = View.GONE
                tvAdapter.setTV(tvPopular)
                tvAdapter.notifyDataSetChanged()

                with(rvFilms){
                    layoutManager = GridLayoutManager(context,3)
                    setHasFixedSize(true)
                    adapter = tvAdapter
                }
            })

            viewModel.getUpcomingTv().observe(viewLifecycleOwner, {
                tvUpcoming ->
                progressBar.visibility = View.GONE
                tvHorizontalAdapter.setMovie(tvUpcoming)
                tvHorizontalAdapter.notifyDataSetChanged()

                with(rvHorFilms){
                    layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
                    adapter = tvHorizontalAdapter
                    setHasFixedSize(true)
                }
            })
        }
    }
}