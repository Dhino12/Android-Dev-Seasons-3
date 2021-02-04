package com.example.myviewmodel

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.ExpectedException

class MainViewModelsTest {

    private lateinit var mainViewModels: MainViewModels

    @Before
    fun init(){
        mainViewModels = MainViewModels()
    }

    // ========= Mengatasi NFE karena tipe angka yang berbeda ===========
    // Annotation @Rule digunakan untuk untuk menjalankan kode sebelum pengujian dilakukan.
    // Jadi jika tidak diberi anotasi  @Rule pada thrown, maka kode tersebut tidak akan berjalan.
    @get:Rule
    var thrown = ExpectedException.none()

    @Test
    fun calculate() {
        val width = "1"
        val height = "2"
        val length = "3"
        mainViewModels.calculate(width,height,length)
        assertEquals(6,mainViewModels.result)
    }

    @Test
    @Throws(java.lang.NumberFormatException::class)
    fun characterInputCalculateTest(){
        val width = "1"
        val length = "A"
        val height = "3"
        thrown.expect(java.lang.NumberFormatException::class.java)
        thrown.expectMessage("For input string: \"A\"") // Message harus disamakan dengan aslinya
        mainViewModels.calculate(width, height, length)
    }

    @Test
    @Throws(NumberFormatException::class)
    fun emptyInputCalculate(){
        val width = "1"
        val height = ""
        val length = "3"
        thrown.expect(NumberFormatException::class.java)
        thrown.expectMessage("For input string: ") // Message harus disamakan dengan aslinya
        mainViewModels.calculate(width, height, length)
    }

    @Test
    @Throws(NumberFormatException::class)
    fun doubleInputCalculateTest(){
        val width = "1"
        val height = "2.3"
        val length = "3"
        thrown.expect(NumberFormatException::class.java)
        thrown.expectMessage("For input string: \"2.3\"") // Message harus disamakan dengan aslinya
        mainViewModels.calculate(width,height,length)
    }
}