package com.neotica.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.neotica.core.ui.CharacterAdapter
import com.neotica.favorite.databinding.ActivityFavoriteBinding
import com.neotica.rickandmorty.detail.DetailCharacterActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        val characterAdapter = CharacterAdapter()
        characterAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailCharacterActivity::class.java)
            intent.putExtra(DetailCharacterActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        lifecycleScope.launch {
            favoriteViewModel.favoriteCharacter.collect {
                characterAdapter.setData(it)
                binding.viewEmpty.root.visibility =
                    if (it.isNotEmpty()) View.GONE else View.VISIBLE
            }
        }

        with(binding.rvCharacter) {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = characterAdapter
        }

    }
}