package org.kverenev.rickandmortycharacters

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import org.kverenev.rickandmortycharacters.databinding.ActivityMainBinding
import org.kverenev.rickandmortycharacters.network.Character

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.charactersLiveData.observe(this) { state ->
            processCharactersResponse(state)
        }
    }

    private fun processCharactersResponse(state: ScreenState<List<Character>?>) {
        when (state) {
            is ScreenState.Loading -> {
                binding.rvCharacters.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
            is ScreenState.Success -> {
                binding.rvCharacters.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE

                if (state.data != null) {
                    val adapter = MainAdapter(state.data)
                    binding.rvCharacters.adapter = adapter
                    binding.rvCharacters.layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                }
            }
            is ScreenState.Error -> {
                binding.rvCharacters.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                Snackbar.make(binding.root, state.message ?: "Error", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}