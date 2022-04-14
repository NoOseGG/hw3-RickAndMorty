package com.bignerdranch.android.rickandmorty.navigation

import android.os.Bundle
import android.os.PerformanceHintManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import coil.load
import com.bignerdranch.android.rickandmorty.R
import com.bignerdranch.android.rickandmorty.databinding.FragmentInfoBinding
import com.bignerdranch.android.rickandmorty.model.PersonItem.Person

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return FragmentInfoBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val person = arguments?.getSerializable(KEY_PERSON) as Person
        with(binding) {
            avatar.load(person.image)
            name.text = person.name
            status.text = getString(R.string.status, person.status)
            species.text = getString(R.string.species, person.species)
            type.text = getString(R.string.type, person.type)
            gender.text = getString(R.string.gender, person.gender)
            toolbar.title = person.name
        }

        binding.toolbar.setupWithNavController(findNavController())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_PERSON = "person"
    }
}