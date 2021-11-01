package com.karaew.learning.gsp_v2.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.karaew.learning.gsp_v2.Model.ModelEntity

@Dao
interface ModelDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addShop(shop: ModelEntity)

    @Update
    suspend fun updateShop(shop:ModelEntity)

    @Delete
    suspend fun deleteShop(shop: ModelEntity)

    @Query("SELECT * FROM shop_list ORDER BY id ASC")
    fun getAllList(): LiveData<List<ModelEntity>>



}