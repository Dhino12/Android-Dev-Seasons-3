package com.example.mysqliteimage.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.mysqliteimage.R
import com.example.mysqliteimage.model.Food

class FoodListAdapter(val context:Context, val layout:Int, val foodList:ArrayList<Food>) : BaseAdapter(){

    private class ViewHolder {
        var imageView: ImageView? = null
        var txtName: TextView? = null
        var txtPrice: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var row = convertView
        var holder = ViewHolder()

        if(row == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            row = inflater.inflate(layout, null)

            holder.txtName = row.findViewById(R.id.txtName) as TextView
            holder.txtPrice = row.findViewById(R.id.txtPrice) as TextView
            holder.imageView = row.findViewById(R.id.imgFood) as ImageView
            row.tag = holder
        }else{
            holder = row.tag as ViewHolder
        }

        val food = foodList[position]
        holder.txtName?.text = food.name
        holder.txtPrice?.text = food.price

        val foodImage = food.image
        val bitmap = BitmapFactory.decodeByteArray(foodImage,0 ,foodImage.size)
        holder.imageView?.setImageBitmap(bitmap)

        return row
    }

    override fun getItem(position: Int): Any = foodList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = foodList.size
}