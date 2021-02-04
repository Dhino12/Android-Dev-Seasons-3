package com.example.myacademy.view.reader.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myacademy.R
import com.example.myacademy.modelData.ModuleEntity

class ModuleListAdapter internal constructor(private val listener:MyAdapterClickListener):RecyclerView.Adapter<ModuleListAdapter.ModuleViewHolder>(){

    private val listModule = ArrayList<ModuleEntity>()

    internal fun setModule(modules:List<ModuleEntity>?){
        if(modules == null) return
        listModule.clear()
        listModule.addAll(modules)
    }

    inner class ModuleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val textTitle:TextView = itemView.findViewById(R.id.text_module_titleCustom)
        fun bind(modules: ModuleEntity){
            textTitle.text = modules.title
            Log.d("test","${listModule.size}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_module_list_custom, parent,false)
        return ModuleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        holder.bind(listModule[position])

        // Pada kelas CourseReaderAdapter terdapat sebuah interface MyAdapterClickListener yang
            // nantinya digunakan untuk berpindah ke halaman ModuleContentFragment.
        holder.itemView.setOnClickListener {
            listener.onItemClicked(holder.adapterPosition, listModule[holder.adapterPosition].moduleId)
        }
    }

    override fun getItemCount(): Int = listModule.size
}

internal interface MyAdapterClickListener{
    fun onItemClicked(position: Int,moduleId:String)
}