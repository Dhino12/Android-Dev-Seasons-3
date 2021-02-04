package com.example.myapplication.ui.bookmark


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.source.local.entity.CourseEntity
import com.example.myapplication.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_bookmark.*

class BookmarkFragment : Fragment(), BookmarkFragmentCallback {

    private lateinit var viewModel :BookmarkViewModel
    private lateinit var adapters :BookmarkAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // ============ Membuat Swiped ==============
        itemTouchHelper.attachToRecyclerView(rv_bookmark)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())

            viewModel = ViewModelProvider(this, factory)[BookmarkViewModel::class.java]
            adapters = BookmarkAdapter(this)

            progress_bar.visibility = View.VISIBLE

            viewModel.getBookmarks().observe(this, Observer{ courses ->
                progress_bar.visibility = View.GONE
                adapters.submitList(courses)
                adapters.notifyDataSetChanged()
            })

            with(rv_bookmark) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = adapters
            }
        }
    }

    // ============ Membuat Swiped ==============
    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback(){
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            // Aksi di bawah digunakan untuk melakukan swap ke kenan dan ke kiri
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean =
            true


        // ============ Membuat Swiped ==============
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if(view != null){
                // Sebelum melakukan penghapusan, course harus mendapatkan posisi dari item yang di swipe
                val swipedPosition = viewHolder.adapterPosition

                // Kemudian memanggil CourseEntity sesuai posisi ketika diswipe
                val courseEntity = adapters.getSwipedData(swipedPosition)

                // Melakukan setBookmark untuk menghapus bookmark dari list course
                courseEntity?.let {
                    viewModel.setBookmark(it)
                }

                // Memanggil Snackbar untuk melakukan pengecekan, apakah benar melakukan penghapusan bookmark
                val snackBar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)

                // Mengembalikan item yang terhapus
                snackBar.setAction(R.string.message_ok){
                    v -> courseEntity?.let { viewModel.setBookmark(it) }
                }

                // Menampilkan snackbar
                snackBar.show()
            }
        }

    })

    override fun onShareClick(course: CourseEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                    .from(activity!!)
                    .setType(mimeType)
                    .setChooserTitle("Bagikan aplikasi ini sekarang.")
                    .setText("Segera daftar kelas ${course.title} di dicoding.com")
                    .startChooser()
        }
    }
}

