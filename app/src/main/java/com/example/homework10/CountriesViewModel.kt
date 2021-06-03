package com.example.homework10

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class CountriesViewModel : ViewModel() {

    private val countriesLiveData =
        MutableLiveData<DataResult<List<Countries>>>().apply { mutableListOf<Countries>() }
    val _countriesLiveData: LiveData<DataResult<List<Countries>>> = countriesLiveData


    fun init() {
        CoroutineScope(Dispatchers.IO).launch {
            countries()
        }
    }

    private suspend fun countries() {
        try {
            countriesLiveData.postValue(DataResult.loading(true))

            val response = RetrofitService.retrofitService().getCountry()
            if (response.isSuccessful) {
                val countriesItems = response.body()
                countriesLiveData.postValue(DataResult.success(countriesItems!!))
            } else {
                response.errorBody()
                countriesLiveData.postValue(DataResult.error(response.message()))
            }
        } catch (e: Exception) {
            Log.d("mainlog", "${e.message}")
        }
    }
}