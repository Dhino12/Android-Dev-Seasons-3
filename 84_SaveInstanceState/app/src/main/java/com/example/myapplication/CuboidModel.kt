package com.example.myapplication

class CuboidModel {
    private var panjang:Double = 0.0
    private var lebar:Double = 0.0
    private var tinggi:Double = 0.0

    fun getVolume():Double = panjang * lebar * tinggi
    fun getSurface():Double{
        val wl = panjang * lebar
        val wh = panjang * tinggi
        val lh = lebar * tinggi
        return 2 * (wl + wh + lh)
    }

    fun getCirumFerence():Double = 4 * ( panjang + lebar + tinggi)

    fun save(p:Double,l:Double,t:Double){
        this.panjang = p
        this.lebar = l
        this.tinggi = t
    }
}