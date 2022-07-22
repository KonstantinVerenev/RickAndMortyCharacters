package org.kverenev.rickandmortycharacters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.kverenev.rickandmortycharacters.network.ApiClient
import org.kverenev.rickandmortycharacters.network.Character
import org.kverenev.rickandmortycharacters.network.CharacterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: Repository = Repository(ApiClient.apiService)) :
    ViewModel() {

    private var _charactersLiveData = MutableLiveData<List<Character>>()
    val charactersLiveData: LiveData<List<Character>> = _charactersLiveData

    init{
        fetchCharacters()
    }

    private fun fetchCharacters() {
        val client = repository.getCharacters("1")
        client.enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful) {
                    _charactersLiveData.postValue(response.body()?.result)
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e("Request Error ", t.toString())
            }

        })
    }
}