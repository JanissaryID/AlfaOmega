package com.example.alfaomega.proto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProtoViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ProtoRepository(application)

    val getData = repository.readProto.asLiveData()

    fun updateStoreID(keyStore: String) = viewModelScope.launch(Dispatchers.IO){
        repository.updateStore(keyStore = keyStore)
    }

//    fun updateWashTimeGiant(WashTimeGiant: Int) = viewModelScope.launch(Dispatchers.IO){
//        repository.updateWashTimeGiant(WashTimeGiant = WashTimeGiant)
//    }
//
//    fun updateDryTimeGiant(DryTimeGiant: Int) = viewModelScope.launch(Dispatchers.IO){
//        repository.updateDryTimeGiant(DryTimeGiant = DryTimeGiant)
//    }
//
//    fun updateWashTimeTitan(WashTimeTitan: Int) = viewModelScope.launch(Dispatchers.IO){
//        repository.updateWashTimeTitan(WashTimeTitan = WashTimeTitan)
//    }
//
//    fun updateDryTimeTitan(DryTimeTitan: Int) = viewModelScope.launch(Dispatchers.IO){
//        repository.updateDryTimeTitan(DryTimeTitan = DryTimeTitan)
//    }
}