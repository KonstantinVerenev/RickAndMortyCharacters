package org.kverenev.rickandmortycharacters

import android.app.Application
import org.kverenev.rickandmortycharacters.data.Repository
import org.kverenev.rickandmortycharacters.network.ApiClient

class App : Application() {

    val repository = Repository(ApiClient.apiService)
}