package com.example.myapplication.ui.reader.content


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.data.source.local.entity.ModuleEntity
import com.example.myapplication.data.source.vo.Status
import com.example.myapplication.ui.reader.CourseReaderViewModel
import com.example.myapplication.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_module_content.*

class ModuleContentFragment : Fragment() {

    private lateinit var viewModel:CourseReaderViewModel

    companion object {
        val TAG = ModuleContentFragment::class.java.simpleName

        fun newInstance(): ModuleContentFragment {
            return ModuleContentFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_module_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(requireActivity(), factory)[CourseReaderViewModel::class.java]

            progress_bar.visibility = View.VISIBLE
            viewModel.selectedModule.observe(this, Observer{ moduleEntity ->
                if (moduleEntity != null) {
                    when(moduleEntity.status){
                        Status.LOADING -> progress_bar.visibility = View.VISIBLE
                        Status.SUCCESS ->
                            if(moduleEntity.data != null){
                                progress_bar.visibility = View.GONE
                            if(moduleEntity.data.contentEntity != null){
                                populateWebView(moduleEntity.data)
                            }
                                setButtonNextPrevState(moduleEntity.data)
                                if(!moduleEntity.data.read){
                                    viewModel.readContent(moduleEntity.data)
                                }
                        }
                        Status.ERROR -> {
                            progress_bar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }

                    btn_next.setOnClickListener { viewModel.setNextPage() }

                    btn_prev.setOnClickListener { viewModel.setPrevPage() }
                }
            })
        }
    }

    private fun setButtonNextPrevState(module: ModuleEntity){
        if(activity != null){
            when(module.position){
                0 -> {
                    btn_prev.isEnabled = false
                    btn_next.isEnabled = true
                }
                viewModel.getModuleSize() - 1 ->{
                    btn_prev.isEnabled = true
                    btn_next.isEnabled = false
                }
                else -> {
                    btn_prev.isEnabled = true
                    btn_next.isEnabled = true
                }
            }
        }
    }

    private fun populateWebView(module: ModuleEntity) {
        web_view.loadData(module.contentEntity?.content!!, "text/html", "UTF-8")
    }
}
