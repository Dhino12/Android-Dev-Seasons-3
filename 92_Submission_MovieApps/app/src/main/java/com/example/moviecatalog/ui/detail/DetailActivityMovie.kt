package com.example.moviecatalog.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalog.R
import com.example.moviecatalog.data.MovieEntity
import com.example.moviecatalog.data.TvEntity
import com.example.moviecatalog.viewmodel.DetailMovieViewModel
import com.example.moviecatalog.viewmodel.DetailTVViewModel
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivityMovie : AppCompatActivity() {
    companion object{
        const val EXTRA_KEY_TV = "theTVKey"
        const val EXTRA_KEY_MOVIE = "theMovieKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val extras = intent.extras
        val titleMovie = extras?.getString(EXTRA_KEY_MOVIE)
        val titleTv = extras?.getString(EXTRA_KEY_TV)?: "Kosong Om"

        if(titleMovie != null){
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailMovieViewModel::class.java]
            if(extras != null){

                viewModel.setSelectedFilms(titleMovie)
                implementationToView(viewModel.getFilms())
            }
        }
        else{
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailTVViewModel::class.java]
            if(extras != null){

                viewModel.setSelectedFilms(titleTv)
                implementationToView(tvEntity = viewModel.getFilms())
            }
        }
    }

    private fun implementationToView(movieEntity:MovieEntity? = null, tvEntity: TvEntity? = null){

        if(movieEntity != null){
            tvTitleFilms.text = movieEntity.titleMovie
            tv_yearsRelease.text = movieEntity.yearPublish
            tv_duration.text = movieEntity.duration
            tv_director.text = movieEntity.director
            tv_screenPlay.text = movieEntity.screenPlay
            tv_ReleaseDateDetail.text = movieEntity.dateRelease
            tv_synopsis.text = movieEntity.description
            movieEntity.posterPath.let { img_coverDetail.setImageResource(it) }
            movieEntity.posterPath.let { img_posterDetail.setImageResource(it) }

            btnImg_share.setOnClickListener {
                val mimeType = "text/plan"
                ShareCompat.IntentBuilder.from(this).apply {
                    setType(mimeType)
                    setChooserTitle("Bagikan Film ini Sekarang")
                    setText(resources.getString(R.string.shareText, movieEntity.titleMovie))
                    startChooser()
                }
            }
        }else{
            tvTitleFilms.text = tvEntity?.titleTv
            tv_yearsRelease.text = tvEntity?.yearPublish
            tv_duration.text = tvEntity?.duration
            tv_director.text = tvEntity?.writer
            tv_screenPlay.text = tvEntity?.kreator
            tv_ReleaseDateDetail.text = tvEntity?.broadcast_date
            tv_synopsis.text = tvEntity?.description
            tvEntity?.posterPath?.let { img_coverDetail.setImageResource(it) }
            tvEntity?.posterPath?.let { img_posterDetail.setImageResource(it) }

            btnImg_share.setOnClickListener {
                val mimeType = "text/plan"
                ShareCompat.IntentBuilder.from(this).apply {
                    setType(mimeType)
                    setChooserTitle("Bagikan Film ini Sekarang")
                    setText(resources.getString(R.string.shareText, tvEntity?.titleTv))
                    startChooser()
                }
            }
        }
    }
}