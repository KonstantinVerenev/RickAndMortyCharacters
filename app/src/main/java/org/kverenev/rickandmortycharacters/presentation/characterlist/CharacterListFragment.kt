package org.kverenev.rickandmortycharacters.presentation.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import org.kverenev.rickandmortycharacters.App
import org.kverenev.rickandmortycharacters.databinding.FragmentCharacterListBinding
import org.kverenev.rickandmortycharacters.domain.models.CharacterItem
import org.kverenev.rickandmortycharacters.presentation.ScreenState
import org.kverenev.rickandmortycharacters.presentation.ViewModelFactory
import org.kverenev.rickandmortycharacters.presentation.navigator

class CharacterListFragment : Fragment() /*, CharacterListAdapter.OnItemClickListener */ {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null"
        }

    private val viewModel: CharacterListViewModel by viewModels { ViewModelFactory(requireContext().applicationContext as App) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.charactersLiveData.observe(viewLifecycleOwner) { state ->
            processCharactersResponse(state)
        }
    }

    private fun processCharactersResponse(state: ScreenState<List<CharacterItem>>) {
        when (state) {
            is ScreenState.Loading -> {
                binding.rvCharacters.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
            is ScreenState.Success -> {
                binding.rvCharacters.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE

                if (state.data != null) {
                    val adapter =
                        CharacterListAdapter(state.data) { id -> navigator().showDetailsScreen(id) }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}