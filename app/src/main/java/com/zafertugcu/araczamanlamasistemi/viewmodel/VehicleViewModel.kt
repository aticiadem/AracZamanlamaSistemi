package com.zafertugcu.araczamanlamasistemi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.zafertugcu.araczamanlamasistemi.data.VehicleDatabase
import com.zafertugcu.araczamanlamasistemi.data.VehicleRepository
import com.zafertugcu.araczamanlamasistemi.model.VehicleInfoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VehicleViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<VehicleInfoModel>>
    private val repository: VehicleRepository

    init {
        val vehicleDao = VehicleDatabase.getDatabase(application).vehicleDao()
        repository = VehicleRepository(vehicleDao)
        readAllData = repository.readAllData
    }

    fun addVehicle(vehicle: VehicleInfoModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addVehicle(vehicle)
        }
    }

}