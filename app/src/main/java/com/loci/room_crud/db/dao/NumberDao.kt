package com.loci.room_crud.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.loci.room_crud.db.entity.NumberEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NumberDao {

    //CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun create(numberEntity: NumberEntity)

    @Query("SELECT * FROM number_table")
    fun read() : Flow<List<NumberEntity>>

    @Update
    fun update(numberEntity: NumberEntity)

    @Delete
    fun delete(numberEntity: NumberEntity)

}