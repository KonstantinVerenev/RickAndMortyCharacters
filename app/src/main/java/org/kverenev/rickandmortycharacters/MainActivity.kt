package org.kverenev.rickandmortycharacters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.kverenev.rickandmortycharacters.databinding.ActivityMainBinding
import org.kverenev.rickandmortycharacters.network.ApiClient
import org.kverenev.rickandmortycharacters.network.CharacterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.charactersLiveData.observe(this) { charactersList ->
            val adapter = MainAdapter(charactersList)
            binding.rvCharacters.adapter = adapter
            binding.rvCharacters.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }
}