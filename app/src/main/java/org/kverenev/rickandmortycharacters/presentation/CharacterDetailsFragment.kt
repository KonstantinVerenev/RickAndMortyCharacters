package org.kverenev.rickandmortycharacters.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.google.android.material.snackbar.Snackbar
import org.kverenev.rickandmortycharacters.App
import org.kverenev.rickandmortycharacters.data.CharacterDetails
import org.kverenev.rickandmortycharacters.databinding.FragmentCharacterDetailsBinding
import kotlin.properties.Delegates

class CharacterDetailsFragment : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null"
        }

    //    var characterId: Int? = null
    private var characterId by Delegates.notNull<String>()

    private val viewModel: CharacterDetailsViewModels by viewModels {
        ViewModelFactory(
            requireContext().applicationContext as App,
            characterId
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        characterId = savedInstanceState?.getString(KEY_ID) ?: arguments?.getString(ARG_ID)
                ?: throw IllegalArgumentException("You need to specify id to launch this fragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.characterDetailsLiveData.observe(viewLifecycleOwner) { state ->
            processCharacterDetailsResponse(state)
        }

        binding.backButton.setOnClickListener {
            navigator().goBack()
        }
    }

    private fun processCharacterDetailsResponse(state: ScreenState<CharacterDetails>) {
        when (state) {
            is ScreenState.Loading -> {
                binding.descriptionGroup.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
            is ScreenState.Success -> {
                binding.descriptionGroup.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE

                if (state.data != null) {
                    binding.characterName.text = "Name: ${state.data.name}"
                    binding.characterSpecies.text = "Species: ${state.data.species}"
                    binding.characterGender.text = "Gender: ${state.data.gender}"
                    binding.characterStatus.text = "Status: ${state.data.status}"
                    binding.characterImage.load(state.data.image)
                }
            }
            is ScreenState.Error -> {
                binding.descriptionGroup.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
                Snackbar.make(binding.root, state.message ?: "Error", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_ID, characterId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_ID = "ARG_ID"
        private const val KEY_ID = "KEY_ID"

        fun newInstance(id: String): CharacterDetailsFragment {
            val args = Bundle()
            args.putString(ARG_ID, id)
            val fragment = CharacterDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}