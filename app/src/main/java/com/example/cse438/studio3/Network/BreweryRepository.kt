package com.example.cse438_rest_studio.Network

import androidx.lifecycle.MutableLiveData
import com.example.cse438_rest_studio.data.Brewery
import com.example.cse438_rest_studio.data.BreweryPayload
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import android.util.Log

class BreweryRepository {
    val service = ApiClient.makeRetrofitService()
    //TODO #6: Create a function that launches a coroutine for searching by city
    fun getBreweriesByCity(resBody: MutableLiveData<List<Brewery>>, city: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getBreweriesByCity(city)

            withContext(Dispatchers.Main) {
                try{
                    if(response.isSuccessful) {
                        resBody.value = response.body()
                        Log.d("GOT RESPONSE", response.body().toString())
                    }
                } catch (e: HttpException) {
                    println("Http error")
                }
            }
        }
    }
    //TODO #7: Create a function that launches a coroutine for searching by a generic term
    fun breweriesSearch(resBody: MutableLiveData<List<Brewery>>, term: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.breweriesSearch(term)

            withContext(Dispatchers.Main) {
                try{
                    if(response.isSuccessful) {
                        resBody.value = response.body()
                    }
                } catch (e: HttpException) {
                    println("Http error")
                }
            }
        }
    }
}