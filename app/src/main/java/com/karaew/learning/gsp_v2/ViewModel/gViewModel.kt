package com.karaew.learning.gsp_v2.ViewModel

import android.app.Application

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.karaew.learning.gsp_v2.Database.ModelDatabase
import com.karaew.learning.gsp_v2.Model.ModelEntity
import com.karaew.learning.gsp_v2.Repository.ModelRepository
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response


class gViewModel(application: Application):AndroidViewModel(application) {

val readListShop: LiveData<List<ModelEntity>>
private val repository: ModelRepository
init {

val listShopDao =  ModelDatabase.getDatabase(application).shopDao()
repository = ModelRepository(listShopDao)
    readListShop = repository.readAllShopList

}



    fun addShop(shop: ModelEntity){

        viewModelScope.launch(Dispatchers.IO){

            repository.addShop(shop)

        }


    }
    fun  update(shop: ModelEntity){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateShop(shop)
        }
    }


    fun deleteShop(shop:ModelEntity){

        viewModelScope.launch(Dispatchers.IO){
            repository.deleteShop(shop)
        }

    }


 fun retrofit(shop: ModelEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                repository.api.postDataApi(shop)
                    .enqueue(object : retrofit2.Callback<List<ModelEntity>> {
                        override fun onResponse(
                            call: Call<List<ModelEntity>>?,
                            response: Response<List<ModelEntity>>?
                        ) {
                            Log.d("retrofit", "Data: ${response.toString()}")
                        }

                        override fun onFailure(call: Call<List<ModelEntity>>?, t: Throwable?) {
                            Log.d("retrofit", "Error: ${t}")

                        }

                    })
            } catch (e: SocketTimeoutException) {

                e.printStackTrace()

            }

        }


    }


}
