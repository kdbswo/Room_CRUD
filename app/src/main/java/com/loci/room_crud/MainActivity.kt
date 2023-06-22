package com.loci.room_crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.loci.room_crud.db.entity.NumberEntity
import com.loci.room_crud.view.CustomAdapter
import com.loci.room_crud.view.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    lateinit var numberArrayList: ArrayList<NumberEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val createBtn = findViewById<Button>(R.id.create)

        createBtn.setOnClickListener {
            val ranNumber = (0..100).random().toString()
            val numberEntity = NumberEntity(0, ranNumber)
            viewModel.create(numberEntity)
        }

        val numberRV = findViewById<RecyclerView>(R.id.numberRV)

        viewModel.read()
        viewModel.numberEntityList.observe(this, Observer {
            Log.d("MAIN", it.toString())

            numberArrayList = it as ArrayList<NumberEntity>
            val customAdapter = CustomAdapter(numberArrayList)
            numberRV.adapter = customAdapter

            onClickEventHandling(customAdapter)
        })
        numberRV.layoutManager = LinearLayoutManager(this)

    }

    private fun onClickEventHandling(customAdapter: CustomAdapter) {

        customAdapter.updateClick = object : CustomAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                viewModel.update(numberArrayList[position])
            }

        }

        customAdapter.deleteClick = object : CustomAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                Toast.makeText(
                    this@MainActivity,
                    numberArrayList[position].toString(),
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.delete(numberArrayList[position])
            }

        }

    }


}





















