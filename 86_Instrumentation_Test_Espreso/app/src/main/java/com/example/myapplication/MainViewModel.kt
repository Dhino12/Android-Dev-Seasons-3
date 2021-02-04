package com.example.myapplication

class MainViewModel(private val cuboidModel: CuboidModel) {
    fun getCircumference() = cuboidModel.getCircumFerence()
    fun getSurfaceArea() = cuboidModel.getSurfaceArea()
    fun getVolume() = cuboidModel.getVolume()

    fun save(i :Double, w : Double, h: Double){
        cuboidModel.save(i,w,h)
    }
}