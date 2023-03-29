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

    fun updateNameUser(Nameuser: String) = viewModelScope.launch(Dispatchers.IO){
        repository.updateNameUser(Nameuser = Nameuser)
    }
//
    fun updateTypeUser(TypeUser: Int) = viewModelScope.launch(Dispatchers.IO){
        repository.updateTypeUser(Typeuser = TypeUser)
    }

    fun updateAddressDevice(AddressDevice: String) = viewModelScope.launch(Dispatchers.IO){
        repository.updateAddressDevice(AddressDevice = AddressDevice)
    }

    fun updateOwnerId(OwnerId: String) = viewModelScope.launch(Dispatchers.IO){
        repository.updateOwnerId(OwnerID = OwnerId)
    }

    fun updateUUID(UUID_Device: String) = viewModelScope.launch(Dispatchers.IO){
        repository.updateUUID(UUID_Device = UUID_Device)
    }

    fun updateEditMode(status: Boolean) = viewModelScope.launch(Dispatchers.IO){
        repository.updateEditMode(status = status)
    }

    fun updateUserId(UserId: String) = viewModelScope.launch(Dispatchers.IO){
        repository.updateUserId(UserID = UserId)
    }
}