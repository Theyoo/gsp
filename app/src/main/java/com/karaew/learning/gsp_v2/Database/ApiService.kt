package com.karaew.learning.gsp_v2.Database

import com.karaew.learning.gsp_v2.Model.ModelEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {


    @POST("/add")
    suspend fun postDataApi(@Body shop: ModelEntity):Call<List<ModelEntity>>

}