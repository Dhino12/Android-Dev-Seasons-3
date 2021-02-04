package com.example.myacademy.view.reader.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myacademy.R
import com.example.myacademy.modelData.ContentEntity
import com.example.myacademy.modelData.ModuleEntity
import com.example.myacademy.view.reader.CourseReaderViewModel
import kotlinx.android.synthetic.main.fragment_module_content.*

class ModuleContentFragment : Fragment() {

    companion object{
        val TAG = ModuleContentFragment::class.java.simpleName

        fun newInstance():ModuleContentFragment{
            return ModuleContentFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(activity != null){
            // Jika Anda ganti requireActivity() dengan this,
                // maka Fragment tidak akan mengambil ViewModel dari Activity tetapi akan membuat ViewModel baru.
            val viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())[CourseReaderViewModel::class.java]
            val module = viewModel.getSelectedModule()
            populateWebView(module)
        }
    }

    // Dengan begitu, kelas ModuleContentFragment sudah bisa menampilkan data dummy di WebView.
    private fun populateWebView(module:ModuleEntity){
        // web_view.loadData(module.contentEntity?.content, "text/html","UTF-8") ========== sama saja ==============
        module.contentEntity?.content?.let { web_view.loadData(it, "text/html","UTF-8") }
    }
}