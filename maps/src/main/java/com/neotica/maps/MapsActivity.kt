package com.neotica.maps

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.neotica.core.data.Resource
import com.neotica.maps.databinding.ActivityMapsBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class MapsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapsBinding
    private val viewModel: MapsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(mapsModule)

        supportActionBar?.title = "Tourism Map"

        getTourismData()
    }

    @SuppressLint("SetTextI18n")
    private fun getTourismData(){
        lifecycleScope.launch {
            viewModel.tourism.collect{
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.tvMaps.text = "This is the map of ${it.data?.get(0)?.name}"
                        }
                        is Resource.Error -> {
                            binding.apply {
                                progressBar.visibility = View.GONE
                                tvError.visibility = View.VISIBLE
                                tvError.text = it.message
                            }
                        }
                    }
                }
            }
        }
    }
}