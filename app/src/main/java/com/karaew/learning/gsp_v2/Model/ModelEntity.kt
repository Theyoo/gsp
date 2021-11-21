package com.karaew.learning.gsp_v2.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "shop_list")
data class ModelEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val shop_name: String,
    val shop_adress: String,
    val shop_grade: String,
    val size_samsung: Int,
    val size_other: Int
): Parcelable