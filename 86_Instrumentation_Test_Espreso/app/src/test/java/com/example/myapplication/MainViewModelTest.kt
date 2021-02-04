package com.example.myapplication

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var cuboidModel:CuboidModel

    private val dummyLength = 12.0
    private val dummyWidth = 7.0
    private val dummyHeight = 6.0
    private val dummyVolume = 504.0
    private val dummyCircumference = 100.0
    private val dummySurfaceArea = 396.0

    @Before
    fun before(){
        cuboidModel = mock(CuboidModel::class.java)
        mainViewModel = MainViewModel(cuboidModel)
    }

    @Test
    fun testVolume(){
        cuboidModel = CuboidModel()
        mainViewModel = MainViewModel(cuboidModel)
        mainViewModel.save(dummyWidth,dummyLength,dummyHeight)
        val volume = mainViewModel.getVolume()
        assertEquals(dummyVolume, volume,0.0001)
    }

    @Test
    fun testCircumference(){
        cuboidModel = CuboidModel()
        mainViewModel = MainViewModel(cuboidModel)
        mainViewModel.save(dummyWidth,dummyLength,dummyHeight)
        val circumference = mainViewModel.getCircumference()
        assertEquals(dummyCircumference,circumference,0.0001)
    }
    @Test
    fun testSurfaceArea(){
        cuboidModel = CuboidModel()
        mainViewModel = MainViewModel(cuboidModel)
        mainViewModel.save(dummyWidth,dummyLength,dummyHeight)
        val surface = mainViewModel.getSurfaceArea()
        assertEquals(dummySurfaceArea,surface,0.0001)
    }
    // ==================================================================
    // ====== Menggunakan Mock ========

    @Test
    fun testMockVolume(){
        `when`(mainViewModel.getVolume()).thenReturn(dummyVolume)
        val volume = mainViewModel.getVolume()
        assertEquals(dummyVolume,volume,0.0001)
    }

    @Test
    fun testMockCircumference(){
        `when`(mainViewModel.getCircumference()).thenReturn(dummyCircumference)
        val circumference = mainViewModel.getCircumference()
        assertEquals(circumference,dummyCircumference,0.001)
    }

    @Test
    fun testMockSurfaceArea(){
        `when`(mainViewModel.getSurfaceArea()).thenReturn(dummySurfaceArea)
        val surface = mainViewModel.getSurfaceArea()
        assertEquals(surface,dummySurfaceArea,0.0001)
    }
}