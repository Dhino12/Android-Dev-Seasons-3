package com.example.mysqliteimage.View

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.mysqliteimage.Database.SQLiteHelper
import com.example.mysqliteimage.R
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException

@RequiresApi(Build.VERSION_CODES.P)
class MainActivity : AppCompatActivity() {

    companion object{
        var sqliteHelper :SQLiteHelper? = null
        val REQUEST_CODE_GALLERY = 999

        fun imageViewToByte(image:ImageView):ByteArray{
            val bitmap = (image.drawable as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val byteArray = stream.toByteArray()
            return byteArray
        }
    }
    lateinit var edtName:TextView
    lateinit var edtPrice:TextView
    lateinit var imageView:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtName = findViewById(R.id.edtName)
        edtPrice = findViewById(R.id.edtDesc)
        imageView = findViewById(R.id.imageView)

        sqliteHelper = SQLiteHelper(this,"FoodDB.sqlite",null,1)
        sqliteHelper!!.queryData(
            "CREATE TABLE IF NOT EXISTS FOOD(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name VARCHAR, " +
                    "description VARCHAR, " +
                    "image BLOB" +
                    ")"
        )

        Toast.makeText(this,"${sqliteHelper == null}",Toast.LENGTH_SHORT).show()

        btnChoose.setOnClickListener {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_CODE_GALLERY
            )
        }

        btnAdd.setOnClickListener {
            try {
                sqliteHelper?.insertData(
                    edtName.text.toString().trim(),
                    edtPrice.text.toString().trim(),
                    imageViewToByte(imageView)
                )
                Toast.makeText(applicationContext, "Added Successfully",Toast.LENGTH_SHORT).show()
                edtName.setText("")
                edtPrice.setText("")
                imageView.setImageResource(R.mipmap.ic_launcher)
            }catch (e:Exception){
                e.printStackTrace()
                Toast.makeText(applicationContext, "Failed Added Data",Toast.LENGTH_SHORT).show()
            }
        }

        btnList.setOnClickListener {
            val intent = Intent(this@MainActivity,FoodList::class.java)
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val intent = Intent(Intent.ACTION_PICK)
                intent.setType("image/*")
                startActivityForResult(intent, REQUEST_CODE_GALLERY)
            }else{
                Toast.makeText(applicationContext, "Kamu Belum Menambahkan Access ke Permission Gallery",Toast.LENGTH_SHORT).show()
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == Activity.RESULT_OK && data != null){
            val uri = data.data as Uri
            try {
                val inputStream = contentResolver.openInputStream(uri)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                imageView.setImageBitmap(bitmap)
            }catch (e:FileNotFoundException){
                e.printStackTrace()
                Toast.makeText(applicationContext, "File Tidak Ditemukan",Toast.LENGTH_SHORT).show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}