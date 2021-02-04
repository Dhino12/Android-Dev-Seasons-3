package com.example.moviecatalog.ui.tv_show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalog.R
import com.example.moviecatalog.adapter.TvShowAdapter
import com.example.moviecatalog.viewmodel.TvViewModel
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
            val viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[TvViewModel::class.java]
            val tvShow = viewModel.getTv()

            val tvAdapter = TvShowAdapter()
            tvAdapter.setTV(tvShow)
            with(rvFilms){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
        }
    }
}