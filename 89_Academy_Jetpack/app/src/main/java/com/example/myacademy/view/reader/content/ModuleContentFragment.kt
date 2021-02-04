package com.example.myacademy.view.reader.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myacademy.R
import com.example.myacademy.modelData.ContentEntity
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
            val content = ContentEntity("""
                <h3 class=\"fr-text-border\"> Contoh Content </h3>
                    <p> Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
            """.trimIndent())
            populateWebView(content)
        }
    }

    // Dengan begitu, kelas ModuleContentFragment sudah bisa menampilkan data dummy di WebView.
    private fun populateWebView(contentEntity: ContentEntity){
        web_view.loadData(contentEntity.content, "text/html","UTF-8")
    }
}