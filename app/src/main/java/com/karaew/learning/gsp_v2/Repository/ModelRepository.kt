package com.karaew.learning.gsp_v2.Repository

import androidx.lifecycle.LiveData
import com.karaew.learning.gsp_v2.Database.ApiService
import com.karaew.learning.gsp_v2.Database.ModelDao
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.karaew.learning.gsp_v2.Model.ModelEntity as ModelEntity

class ModelRepository(private val shopDao: ModelDao) {


    val readAllShopList: LiveData<List<ModelEntity>> = shopDao.getAllList()

    var client = OkHttpClient.Builder()
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS).build()

    val api = Retrofit.Builder()
        .baseUrl("http://192.168.0.20:3030")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)


    suspend fun addShop(shop: ModelEntity) {
        shopDao.addShop(shop)

    }

    suspend fun updateShop(shop: ModelEntity) {
        shopDao.updateShop(shop)
    }

    suspend fun deleteShop(shop: ModelEntity) {
        shopDao.deleteShop(shop)
    }


}
