package com.karaew.learning.gsp_v2.Repository

import androidx.lifecycle.LiveData
import com.karaew.learning.gsp_v2.Database.ModelDao
import com.karaew.learning.gsp_v2.Model.ModelEntity

class ModelRepository(private val shopDao: ModelDao) {
    val readAllShopList: LiveData<List<ModelEntity>> = shopDao.getAllList()


   suspend fun addShop(shop: ModelEntity){
        shopDao.addShop(shop)
    }

    suspend fun updateShop(shop: ModelEntity){
        shopDao.updateShop(shop)
    }

    suspend fun deleteShop(shop:ModelEntity){
        shopDao.deleteShop(shop)
    }

}