package com.example.moviecatalog.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalog.R
import com.example.moviecatalog.adapter.SectionPagerAdapter
import com.example.moviecatalog.data.source.local.MovieDetailEntity
import com.example.moviecatalog.data.source.local.TvDetailEntity
import com.example.moviecatalog.viewmodel.DetailMovieViewModel
import com.example.moviecatalog.viewmodel.DetailTVViewModel
import com.example.moviecatalog.viewmodel.ViewModelFactory
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks
import com.github.ksoichiro.android.observablescrollview.ScrollState
import com.github.ksoichiro.android.observablescrollview.ScrollUtils
import com.nineoldandroids.view.ViewHelper
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivityMovie : AppCompatActivity(),ObservableScrollViewCallbacks {
    companion object{
        const val EXTRA_KEY_TV = "theTVKey"
        const val EXTRA_KEY_MOVIE = "theMovieKey"
    }

    private var mParallaxImageHeight:Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val extras = intent.extras
        val idMovie = extras?.getInt(EXTRA_KEY_MOVIE)
        val idTV = extras?.getInt(EXTRA_KEY_TV)

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        viewPaggerDetail.adapter = sectionPagerAdapter
        tabsDetail.setupWithViewPager(viewPaggerDetail)

        scrollViewDetail.setScrollViewCallbacks(this)
        mParallaxImageHeight = resources.getDimensionPixelSize(R.dimen.parallax_image_height)

        if(idMovie != null && idMovie > 0){
            val factory = ViewModelFactory.getInstance(this, this)
            val viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

            sectionPagerAdapter.movieON = true

            viewModel.setSelectedFilms(idMovie)

            viewModel.getDetail().observe(this, { detailFilms ->
                progressBarDetail.visibility = View.GONE
                implementationToView(movieEntityMovie = detailFilms)
            })
        }
        else{
            val factory = ViewModelFactory.getInstance(this, this)
            val viewModels = ViewModelProvider(this, factory)[DetailTVViewModel::class.java]
            if(extras != null){
                viewModels.setSelectedFilms(idTV)
                viewModels.getTvDetail().observe(this, { detailTv ->
                    progressBarDetail.visibility = View.GONE
                    implementationToView(tvEntity = detailTv)
                })
            }
        }
    }

    private fun implementationToView(
        movieEntityMovie: MovieDetailEntity? = null,
        tvEntity: TvDetailEntity? = null
    ){

        if(movieEntityMovie != null){
            tvTitleFilms.text = movieEntityMovie.title
            tv_yearsRelease.text = movieEntityMovie.releaseDate

            Glide.with(this)
                .load(resources.getString(R.string.linkPoster) + movieEntityMovie.posterPath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img_posterDetail)
            Glide.with(this)
                .load(resources.getString(R.string.linkPoster) + movieEntityMovie.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.baseline_hourglass_bottom_black_18dp)
                        .error(
                            R.drawable.ic_error
                        )
                )
                .into(img_coverDetail)

            btnImg_share.setOnClickListener {
                val mimeType = "text/plan"
                ShareCompat.IntentBuilder.from(this).apply {
                    setType(mimeType)
                    setChooserTitle(resources.getString(R.string.shareTitle))
                    setText(resources.getString(R.string.shareText, movieEntityMovie.title))
                    startChooser()
                }
            }
        }else{
            tvTitleFilms.text = tvEntity?.originalName
            tv_yearsRelease.text = tvEntity?.firstAirDate
            Glide.with(this)
                .load(resources.getString(R.string.linkPoster) + tvEntity?.posterPath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img_posterDetail)

            Glide.with(this)
                .load(resources.getString(R.string.linkPoster) + tvEntity?.posterPath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img_coverDetail)

            btnImg_share.setOnClickListener {
                val mimeType = "text/plan"
                ShareCompat.IntentBuilder.from(this).apply {
                    setType(mimeType)
                    setChooserTitle(resources.getString(R.string.shareTitle))
                    setText(resources.getString(R.string.shareText, tvEntity?.originalName))
                    startChooser()
                }
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        onScrollChanged(scrollViewDetail.currentScrollY, firstScroll = false, dragging = false)
    }
    override fun onScrollChanged(scrollY: Int, firstScroll: Boolean, dragging: Boolean) {
        ViewHelper.setTranslationY(img_coverDetail, (scrollY / 2).toFloat())
    }

    override fun onDownMotionEvent() {
    }

    override fun onUpOrCancelMotionEvent(scrollState: ScrollState?) {

    }
}