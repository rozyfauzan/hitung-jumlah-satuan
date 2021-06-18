package org.d3if4079.hitungjumlahsatuan.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.d3if4079.hitungjumlahsatuan.network.ApiStatus
import org.d3if4079.hitungjumlahsatuan.network.DataDiriApi

class MainViewModel : ViewModel (){

    private val data = MutableLiveData<List<DataDiri>>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch {
            status.value = ApiStatus.LOADING
            try {
               data.value = DataDiriApi.service.getDataDiri()
                status.value = ApiStatus.SUCCESS

            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.value = ApiStatus.FAILED

            }
        }
    }


    fun getData() : LiveData<List<DataDiri>> = data

    fun getStatus(): LiveData<ApiStatus> = status


}