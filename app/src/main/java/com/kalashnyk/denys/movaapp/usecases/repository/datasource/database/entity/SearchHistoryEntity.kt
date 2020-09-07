package com.kalashnyk.denys.movaapp.usecases.repository.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kalashnyk.denys.movaapp.base.model.BaseModel

@Entity(tableName = "search")
data class SearchHistoryEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,

    val query: String,

    val imageRemote: String
): BaseModel()