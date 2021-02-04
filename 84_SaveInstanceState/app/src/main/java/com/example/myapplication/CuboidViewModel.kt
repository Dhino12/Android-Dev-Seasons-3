package com.example.myapplication

import com.example.myapplication.CuboidModel

class CuboidViewModel(private val cuboidModel: CuboidModel) {
    fun getVolume():Double = cuboidModel.getVolume()
    fun getSurface():Double = cuboidModel.getSurface()
    fun getCirumFerence():Double = cuboidModel.getCirumFerence()

    fun save(panjang:Double,lebar:Double,tinggi:Double) {
        cuboidModel.save(panjang,lebar,tinggi)
    }
}