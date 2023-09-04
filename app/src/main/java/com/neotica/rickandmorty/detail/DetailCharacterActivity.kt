package com.neotica.rickandmorty.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.neotica.core.domain.model.Character
import com.neotica.rickandmorty.R
import com.neotica.rickandmorty.databinding.ActivityDetailCharacterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailCharacterActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailCharacterViewModel: DetailCharacterViewModel by viewModel()
    private lateinit var binding: ActivityDetailCharacterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        val detailCharacter = intent.getParcelableExtra<Character>(EXTRA_DATA)
        showDetailCharacter(detailCharacter)
    }

    @SuppressLint("SetTextI18n")
    private fun showDetailCharacter(detailCharacter: Character?) {
        detailCharacter?.let {
            supportActionBar?.title = detailCharacter.name
            binding.content.apply {
                tvTitle.text = detailCharacter.name
                tvDetailGender.text = "Gender: " + detailCharacter.gender
                tvDetailDescription.text = "Species: " + detailCharacter.species
                tvDetailStatus.text = "Status: " + detailCharacter.status
                tvDetailType.text = "Type: " + if (detailCharacter.type == "") "Nothing" else detailCharacter.type
            }
            Glide.with(this@DetailCharacterActivity)
                .load(detailCharacter.image)
                .into(binding.ivDetailImage)

            var statusFavorite = detailCharacter.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailCharacterViewModel.setFavoriteCharacter(detailCharacter, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}
