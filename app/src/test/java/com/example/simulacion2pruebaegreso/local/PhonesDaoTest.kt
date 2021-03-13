package com.example.simulacion2pruebaegreso.local

import com.example.simulacion2pruebaegreso.model.local.PhonesDataBase
import org.junit.Rule

class PhonesDaoTest {
    @get:Rule
    //val instantTaskExecutorRule = InstantTaskExecutorRule()
    lateinit var phonesDao: PhonesDaoTest
    lateinit var db: PhonesDataBase
}