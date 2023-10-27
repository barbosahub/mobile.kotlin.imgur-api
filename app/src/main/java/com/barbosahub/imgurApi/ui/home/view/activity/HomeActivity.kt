package com.barbosahub.imgurApi.ui.home.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.barbosahub.imgurApi.databinding.ActivityMainBinding
import com.barbosahub.imgurApi.stateFlow.StateFlow
import com.barbosahub.imgurApi.ui.home.view.adapter.GridAdapter
import com.barbosahub.imgurApi.ui.home.viewModel.GalleryViewModel
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var gridAdapter: GridAdapter
    private val viewModel: GalleryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        initValues()
        initObservables()
    }

    private fun initValues() {
        viewModel.getCatsList()
    }

    private fun initObservables(){
        viewModel.catsList.observe(this){
            when(it) {
                is StateFlow.Loading-> loading(it.loading)
                is StateFlow.Success<*>-> initPokemon(it.data as JSONObject)
                is StateFlow.Error-> error(it.errorMessage)
            }
        }
    }

    private fun initListeners() {

    }

    private fun loading(loading: Boolean){
        binding.progress.visibility = if (loading) VISIBLE else GONE
    }

    private fun initPokemon(result: JSONObject) {
        mockSchedules(result)
        initRecyclerAdapter()
    }

    private fun error(errorMessage: String){
        Toast.makeText(this,"ERROR:$errorMessage",Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecyclerAdapter(){
        with(binding){
            recyclerView.layoutManager = GridLayoutManager(applicationContext,2)
            gridAdapter = GridAdapter(viewModel.listCats)
            recyclerView.adapter = gridAdapter
        }

        initListeners()
    }

    private fun mockSchedules(result: JSONObject){
        viewModel.fillCatsList(result)
    }
}