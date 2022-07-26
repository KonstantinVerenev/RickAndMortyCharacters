package org.kverenev.rickandmortycharacters.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.kverenev.rickandmortycharacters.R
import org.kverenev.rickandmortycharacters.databinding.ActivityMainBinding
import org.kverenev.rickandmortycharacters.presentation.characterdetails.CharacterDetailsFragment
import org.kverenev.rickandmortycharacters.presentation.characterlist.CharacterListFragment

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val fragment = CharacterListFragment()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun showDetailsScreen(characterId: String) {
        launchFragment(CharacterDetailsFragment.newInstance(characterId))
    }

    override fun goBack() {
        onBackPressed()
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}