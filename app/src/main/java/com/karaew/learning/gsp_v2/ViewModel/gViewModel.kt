package com.karaew.learning.gsp_v2.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.karaew.learning.gsp_v2.Database.ModelDatabase
import com.karaew.learning.gsp_v2.Model.ModelEntity
import com.karaew.learning.gsp_v2.Repository.ModelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

}
