package com.example.simulacion2pruebaegreso.remote

import com.example.simulacion2pruebaegreso.model.pojo.Phones
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhonesApiTest {
    lateinit var mockWebServer: MockWebServer
    lateinit var phonesApi: PhonesApiTest
    @Before
    fun setup(){
        mockWebServer = MockWebServer()
        val mRetrofit = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        phonesApi = mRetrofit.create(PhonesApiTest::class.java)
    }
    @After
    fun shutDown(){
        mockWebServer.shutdown()
    }
    @Test
    fun getPhoneList() = runBlocking{
        //given
        val phoneListItem = listOf(Phones(1,"url",750000,"jghjg")
            , Phones(2,"Samsung Galaxy",780000,"jghjg"))
        mockWebServer.enqueue(MockResponse().setBody(Gson().toJson(phoneListItem)))
        //when
        val result = phonesApi
        //then

    }
}