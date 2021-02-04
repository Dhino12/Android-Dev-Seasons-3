package com.example.moviecatalog.ui.tv_show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalog.R
import com.example.moviecatalog.adapter.HorizontalAdapter
import com.example.moviecatalog.adapter.tv.TvSearchAdapter
import com.example.moviecatalog.adapter.tv.TvShowAdapter
import com.example.moviecatalog.data.source.vo.Status
import com.example.moviecatalog.viewmodel.TvViewModel
import com.example.moviecatalog.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_film.*

class TvShowFragment : Fragment() {

    private lateinit var viewModel:TvViewModel
    private lateinit var tvAdapter: TvShowAdapter

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
            viewModel = ViewModelProvider(this,factory)[TvViewModel::class.java]

            tvAdapter = TvShowAdapter()
            getTvPopular()


            val searchAdapter = TvSearchAdapter()
            progressBar.visibility = View.VISIBLE
            search_bars.queryHint = getString(R.string.searchMovie)

            search_bars.setOnSearchClickListener {
                applicationName.visibility = View.GONE
            }
            search_bars.setOnCloseListener {
                applicationName.visibility = View.VISIBLE
                search_bars.queryHint = ""
                rilis.text = resources.getString(R.string.watchNow)
                getTvPopular()
                false
            }

            search_bars.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if(query != null){
                        progressBar.visibility = View.VISIBLE
                        viewModel.getSearchTv(query.trim()).observe(viewLifecycleOwner,{
                                search_movie ->
                            if(search_movie != null){
                                rilis.text = getString(R.string.resultSearch)
                                searchAdapter.setSearch(search_movie)
                                progressBar.visibility = View.GONE
                                searchAdapter.notifyDataSetChanged()

                                with(rvFilms){
                                    layoutManager = GridLayoutManager(context,3)
                                    setHasFixedSize(true)
                                    adapter = searchAdapter
                                }
                            }
                        })
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })

            val tvHorizontalAdapter = HorizontalAdapter()
            viewModel.getUpcomingTv().observe(viewLifecycleOwner, {
                tvUpcoming ->
                if(tvUpcoming.data != null){
                    when(tvUpcoming.status){
                        Status.LOADING -> progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            progressBar.visibility = View.GONE
                            tvHorizontalAdapter.setMovie(tv = tvUpcoming.data)
                            tvHorizontalAdapter.notifyDataSetChanged()

                            with(rvHorFilms){
                                layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
                                adapter = tvHorizontalAdapter
                                setHasFixedSize(true)
                            }
                        }
                        Status.ERROR -> {
                            progressBar.visibility = View.VISIBLE
                            Toast.makeText(context, getString(R.string.error), Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
        }
    }

    private fun getTvPopular(){
        viewModel.getTvPop().observe(viewLifecycleOwner, {
                tvPopular ->
            if(tvPopular != null){
                when(tvPopular.status){
                    Status.LOADING -> progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        tvAdapter.submitList(tvPopular.data)
                        tvAdapter.notifyDataSetChanged()

                        with(rvFilms){
                            layoutManager = GridLayoutManager(context,3)
                            setHasFixedSize(true)
                            adapter = tvAdapter
                        }
                    }
                    Status.ERROR -> {}
                }
            }
        })

    }
}