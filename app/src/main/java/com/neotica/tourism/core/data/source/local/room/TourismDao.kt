package com.neotica.tourism.core.data.source.local.room

import androidx.room.*
import com.neotica.tourism.core.data.source.local.entity.TourismEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TourismDao {

    @Query("SELECT * FROM tourism")
    fun getAllTourism(): Flow<List<TourismEntity>>

    @Query("SELECT * FROM tourism where isFavorite = 1")
    fun getFavoriteTourism(): Flow<List<TourismEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTourism(tourism: List<TourismEntity>)

    @Update
    fun updateFavoriteTourism(tourism: TourismEntity)
}
