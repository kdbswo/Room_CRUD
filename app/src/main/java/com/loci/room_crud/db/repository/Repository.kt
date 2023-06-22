package com.loci.room_crud.db.repository

import com.loci.room_crud.MyApp
import com.loci.room_crud.db.MyDatabase
import com.loci.room_crud.db.entity.NumberEntity

class Repository {

    // CRUD
    private val context = MyApp.context()

    private val db = MyDatabase.getDatabase(context)

    fun create(numberEntity: NumberEntity) = db.numberDao().create(numberEntity)

    fun read() = db.numberDao().read()

    fun update(numberEntity: NumberEntity) = db.numberDao().update(numberEntity)

    fun delete(numberEntity: NumberEntity) = db.numberDao().delete(numberEntity)

}