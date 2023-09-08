package com.neotica.rickandmorty.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.neotica.core.ui.CharacterAdapter
import com.neotica.rickandmorty.R
import com.neotica.rickandmorty.databinding.FragmentHomeBinding
import com.neotica.rickandmorty.detail.DetailCharacterActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding?:throw IllegalStateException("Null binding.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val characterAdapter = CharacterAdapter()
            characterAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailCharacterActivity::class.java)
                intent.putExtra(DetailCharacterActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            viewLifecycleOwner.lifecycleScope.launch {
                homeViewModel.character.collect{
                    when (it) {
                        is com.neotica.core.data.Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is com.neotica.core.data.Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            characterAdapter.setData(it.data)
                        }

                        is com.neotica.core.data.Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                it.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            with(binding.rvCharacter) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = characterAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
