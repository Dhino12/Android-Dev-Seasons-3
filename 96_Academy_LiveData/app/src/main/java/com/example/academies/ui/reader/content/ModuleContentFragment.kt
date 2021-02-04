package com.example.academies.ui.reader.content


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.academies.R
import com.example.academies.data.source.local.entity.ModuleEntity
import com.example.academies.viewmodel.CourseReaderViewModel
import com.example.academies.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_module_content.*


/**
 * A simple [Fragment] subclass.
 */
class ModuleContentFragment : Fragment() {

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
            val viewModel = ViewModelProvider(requireActivity(), factory)[CourseReaderViewModel::class.java]

            progress_bar.visibility = View.VISIBLE
            viewModel.getSelectedModule().observe(this, Observer {
                module -> if(module != null){
                progress_bar.visibility = View.GONE
                populateWebView(module)
            }
            })
        }
    }

    private fun populateWebView(module: ModuleEntity) {
        web_view.loadData(module.contentEntity?.content, "text/html", "UTF-8")
    }
}
