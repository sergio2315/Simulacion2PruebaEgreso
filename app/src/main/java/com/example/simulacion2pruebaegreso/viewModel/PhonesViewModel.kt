package com.example.simulacion2pruebaegreso.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.simulacion2pruebaegreso.model.local.PhonesDataBase
import com.example.simulacion2pruebaegreso.model.pojo.DetailPhones
import com.example.simulacion2pruebaegreso.model.pojo.Phones
import com.example.simulacion2pruebaegreso.model.remote.PhonesRepository
import kotlinx.coroutines.launch

class PhonesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PhonesRepository

    init {
        val dao = PhonesDataBase.getDataBase(application).getPhonesDao()
        repository = PhonesRepository(dao)
        viewModelScope.launch {
            repository.getPhonesWithCourutines()
        }
    }
    fun getPhonesList(): LiveData<List<Phones>> = repository.liveDataDB

    private var mId : Int = 0

    fun selectedDetailPhone(id: Int) = viewModelScope.launch{
        mId = id
        repository.getDetailPhonesWithCourutines(id)
    }
    fun returnDetail(): LiveData<DetailPhones> = repository.getDetailPhonesByID(mId)

}