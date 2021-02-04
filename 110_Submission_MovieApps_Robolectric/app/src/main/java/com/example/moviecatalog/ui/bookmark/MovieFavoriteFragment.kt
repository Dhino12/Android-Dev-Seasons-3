package com.example.moviecatalog.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalog.R
import com.example.moviecatalog.adapter.FavoriteMovieAdapter
import com.example.moviecatalog.viewmodel.FavoriteMovieViewModel
import com.example.moviecatalog.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_favorite.progressBarFavorite
import kotlinx.android.synthetic.main.fragment_favorite_movie.*

class MovieFavoriteFragment : Fragment() {

    private lateinit var viewModel:FavoriteMovieViewModel
    private lateinit var adapters:FavoriteMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        itemTouchHelper.attachToRecyclerView(rvFilms_FavoriteMovie)

        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity(), requireContext())
            viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]

            adapters = FavoriteMovieAdapter()
            progressBarFavorite.visibility = View.VISIBLE
            viewModel.getMovieFavorite().observe(viewLifecycleOwner, Observer {
                movies ->
                    progressBarFavorite.visibility = View.GONE
                    adapters.submitList(movies)
                    adapters.notifyDataSetChanged()

                with(rvFilms_FavoriteMovie){
                    layoutManager = GridLayoutManager(context,3)
                    setHasFixedSize(true)
                    adapter = adapters
                }
            })
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback(){
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            return makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        }

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if(view != null){
                val swipedPosition = viewHolder.adapterPosition
                val movieDetailEntity = adapters.getSwipeData(swipedPosition)
                movieDetailEntity?.let { viewModel.setFavorite(it) }

                val snackbar = Snackbar.make(view as View, getString(R.string.undo), Snackbar.LENGTH_LONG)
                snackbar.setAction(getString(R.string.ok)){
                    movieDetailEntity?.let { viewModel.setFavorite(it)
                    }
                }
                snackbar.show()
            }
        }

    })
}