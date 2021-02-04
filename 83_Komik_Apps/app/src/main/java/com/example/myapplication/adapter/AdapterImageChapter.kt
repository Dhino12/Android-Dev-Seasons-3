package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target
import com.example.myapplication.R
import com.example.myapplication.model.ModelChapter

class AdapterImageChapter(private val items:List<ModelChapter>,private val mContext:Context):PagerAdapter() {

    private var layoutInflater:LayoutInflater? = null

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = items.size


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater?.inflate(R.layout.list_item_detail_chapter,container,false)

        val data = items[position]
        val imageView = view?.findViewById<ImageView>(R.id.imgPhotoChapter)
        val tvPagination = view?.findViewById<TextView>(R.id.tvPagination)
        tvPagination?.text = "Hal : ${data.imageNumber}"

        Glide.with(mContext)
            .load(data.chapterImage)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .override(Target.SIZE_ORIGINAL)
            .into(imageView!!)
        container.addView(view,0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}