package com.example.myacademy.view.reader.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myacademy.R
import com.example.myacademy.modelData.ModuleEntity
import com.example.myacademy.utils.DataDummy
import com.example.myacademy.view.reader.CourseReaderActivity
import com.example.myacademy.view.reader.CourseReaderCallback
import kotlinx.android.synthetic.main.fragment_module_list.*

class ModuleListFragment : Fragment(),MyAdapterClickListener {

    companion object{
        val TAG = ModuleListFragment::class.java.simpleName

        fun newInstance():ModuleListFragment = ModuleListFragment()
    }

    private lateinit var adapters:ModuleListAdapter
    private lateinit var courseReaderCallback:CourseReaderCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapters = ModuleListAdapter(this)
        Toast.makeText(activity,"Masuk ListFragment", Toast.LENGTH_SHORT).show()
        populateRecyclerView(DataDummy.generateDummyModules("a14"))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        courseReaderCallback = context as CourseReaderActivity
    }

    override fun onItemClicked(position: Int, moduleId: String) {
        courseReaderCallback.moveTo(position,moduleId)
    }

    private fun populateRecyclerView(modules:List<ModuleEntity>){
        progress_bar_moduleList.visibility = View.GONE
        adapters.setModule(modules)
        with(rv_moduleList){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = adapters
        }
        val dividerItemDecoration = DividerItemDecoration(rv_moduleList.context, DividerItemDecoration.VERTICAL)
        rv_moduleList.addItemDecoration(dividerItemDecoration)
    }

}