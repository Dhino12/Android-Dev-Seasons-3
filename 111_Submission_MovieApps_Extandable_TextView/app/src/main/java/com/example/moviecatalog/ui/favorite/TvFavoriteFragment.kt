package com.example.moviecatalog.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalog.R
import com.example.moviecatalog.adapter.favorite.FavoriteTvAdapter
import com.example.moviecatalog.viewmodel.FavoriteTvViewModel
import com.example.moviecatalog.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_favorite.*


class TvFavoriteFragment : Fragment() {

    private lateinit var adapters: FavoriteTvAdapter
    private lateinit var viewModel:FavoriteTvViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        itemTouchHelper.attachToRecyclerView(rvFilmsFavorite)

        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity(), requireContext())
            viewModel = ViewModelProvider(this, factory)[FavoriteTvViewModel::class.java]

            adapters = FavoriteTvAdapter()
            progressBarFavorite.visibility = View.VISIBLE
            viewModel.getMovieFavorite().observe(viewLifecycleOwner, {
                    tv ->
                progressBarFavorite.visibility = View.GONE
                adapters.submitList(tv)
                adapters.notifyDataSetChanged()


                with(rvFilmsFavorite){
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
                val tvDetailEntity = adapters.getSwipeData(swipedPosition)
                tvDetailEntity?.let { viewModel.setFavorite(it) }

                val snackbar = Snackbar.make(view as View, getString(R.string.undoDelete), Snackbar.LENGTH_LONG)
                snackbar.setAction(getString(R.string.ok)){
                        tvDetailEntity?.let { viewModel.setFavorite(it)
                    }
                }
                snackbar.show()
            }
        }

    })
}