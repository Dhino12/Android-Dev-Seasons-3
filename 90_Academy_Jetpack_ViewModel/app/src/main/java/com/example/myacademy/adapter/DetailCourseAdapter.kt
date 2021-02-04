package com.example.myacademy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myacademy.R
import com.example.myacademy.modelData.ModuleEntity
import kotlinx.android.synthetic.main.items_module_list.view.*

class DetailCourseAdapter : RecyclerView.Adapter<DetailCourseAdapter.ModuleViewHolder>() {

    private val listModule = ArrayList<ModuleEntity>()

    fun setModules(modules:List<ModuleEntity>?){
        if(modules == null) return
        listModule.clear()
        listModule.addAll(modules)
    }

    class ModuleViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
        fun bind(module:ModuleEntity){
            with(itemView){
                text_module_title.text = module.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_module_list,parent,false)
        return ModuleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        holder.bind(listModule[position])
    }

    override fun getItemCount(): Int = listModule.size
}