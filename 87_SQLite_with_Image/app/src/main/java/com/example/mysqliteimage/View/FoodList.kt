package com.example.mysqliteimage.View

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.*
import android.widget.AdapterView.OnItemLongClickListener
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.mysqliteimage.R
import com.example.mysqliteimage.adapter.FoodListAdapter
import com.example.mysqliteimage.model.Food
import kotlinx.android.synthetic.main.activity_food_list.*
import java.io.FileNotFoundException

@RequiresApi(Build.VERSION_CODES.P)
class FoodList : AppCompatActivity() {

    var list = ArrayList<Food>()
    var adapter :FoodListAdapter? = null
    private lateinit var imageViewUpdate: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_list)

        adapter = FoodListAdapter(this,R.layout.food_items,list)
        gridView.adapter = adapter

        // get all data from sqlite
        val cursor:Cursor? = MainActivity.sqliteHelper?.getData("SELECT * FROM FOOD")

        list.clear()
        if (cursor != null) {
            while (cursor.moveToNext()){
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val price = cursor.getString(2)
                val image = cursor.getBlob(3)
                list.add(Food(id,name,price,image))
            }
        }
        Toast.makeText(this,"Cursor.isNull(${cursor == null})",Toast.LENGTH_SHORT).show()
        adapter?.notifyDataSetChanged()

        gridView.onItemLongClickListener =
            OnItemLongClickListener { _, _, position, _ ->
                val items = arrayOf<CharSequence>("Update", "Delete")
                val dialog = AlertDialog.Builder(this@FoodList)
                dialog.setTitle("Choose an Action")
                dialog.setItems(items) { dialog, which ->
                    if(which == 0){
                        // update
                        val c = MainActivity.sqliteHelper?.getData("SELECT id FROM FOOD")
                        val arrId = ArrayList<Int>()

                        while (c!!.moveToNext()){
                            arrId.add(c.getInt(0))
                        }

                        // show dialog update at here

                        showDialogUpdate(this@FoodList, arrId[position])
                    }else{
                        // delete
                        val c = MainActivity.sqliteHelper?.getData("SELECT id FROM FOOD")
                        val arrID = ArrayList<Int>()
                        while (c!!.moveToNext()){
                            arrID.add(c.getInt(0))
                        }
                        showDialogDelete(arrID[position])
                    }
                }
                dialog.show()
                true
            }
    }

    private fun showDialogUpdate(activity:Activity, position:Int){
        val dialog = Dialog(activity)
        dialog.setContentView(R.layout.update_food)
        dialog.setTitle("Update")

        // set width for dialog
        val width = (activity.resources.displayMetrics.widthPixels * 0.7).toInt()
        val height = (activity.resources.displayMetrics.heightPixels * 0.7).toInt()
        dialog.window?.setLayout(width,height)
        dialog.show()

        imageViewUpdate = dialog.findViewById(R.id.imageViewFoodUpdate)
        val buttonUpdate = dialog.findViewById<Button>(R.id.btnUpdate)
        val edtNameUpdate = dialog.findViewById<TextView>(R.id.edtNameUpdate)
        val edtPriceUpdate = dialog.findViewById<TextView>(R.id.edtPriceUpdate)

        imageViewUpdate.setOnClickListener {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 888
            )
        }

        buttonUpdate.setOnClickListener {
            try {
                MainActivity.sqliteHelper?.updateData(
                    edtNameUpdate.text.toString().trim(),
                    edtPriceUpdate.text.toString().trim(),
                    MainActivity.imageViewToByte(imageViewUpdate),
                    position
                )
                dialog.dismiss()
                Toast.makeText(applicationContext, "Update successfully!!!", Toast.LENGTH_SHORT).show()
            }catch (error:Exception){
                error.message?.let { it1 -> Log.e("Update Error", it1) }
            }
            updateFoodList()
        }
    }

    private fun showDialogDelete(idFood:Int){
        val dialogDelete = AlertDialog.Builder(this)
        dialogDelete.setTitle("Warning!!")
        dialogDelete.setMessage("Kamu Yakin Menghapus ini ?")
        dialogDelete.setPositiveButton("OK") {
                dialog,which ->
            try {
                MainActivity.sqliteHelper?.deleteData(idFood)
                Toast.makeText(this, "Delete successfully!!!", Toast.LENGTH_SHORT).show()
            }catch (e:Exception){
                e.message?.let { Log.e("error", it) }
            }
            updateFoodList()
        }

        dialogDelete.setNegativeButton("Cancle",DialogInterface.OnClickListener{
                dialog, _ -> dialog.dismiss()
        })
        dialogDelete.show()
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun updateFoodList(){
        // get all data from sqlite
        val cursor = MainActivity.sqliteHelper?.getData("SELECT * FROM FOOD")
        list.clear()
        while (cursor!!.moveToNext()){
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val price = cursor.getString(2)
            val image = cursor.getBlob(3) as ByteArray

            list.add(Food(id,name,price,image))
        }
        adapter?.notifyDataSetChanged()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode === 888){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val intent = Intent(Intent.ACTION_PICK)
                intent.setType("image/*")
                startActivityForResult(intent,888)
            }else{
                Toast.makeText(applicationContext, "Kamu Belum Menambahkan Access ke Permission Gallery",Toast.LENGTH_SHORT).show()
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode === 888 && resultCode == Activity.RESULT_OK && data != null){
            val uri = data.data as Uri
            try {
                val inputStream = contentResolver.openInputStream(uri)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                imageViewUpdate.setImageBitmap(bitmap)
            }catch (e:FileNotFoundException){
                e.printStackTrace()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}