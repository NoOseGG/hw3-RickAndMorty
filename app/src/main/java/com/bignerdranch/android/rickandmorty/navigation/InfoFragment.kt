package com.bignerdranch.android.rickandmorty.navigation

import android.annotation.SuppressLint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.bignerdranch.android.rickandmorty.R
import com.bignerdranch.android.rickandmorty.databinding.FragmentInfoBinding
import com.bignerdranch.android.rickandmorty.model.PersonInfo
import com.bignerdranch.android.rickandmorty.retrofit.RickAndMortyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var load by Delegates.notNull<Call<PersonInfo>>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentInfoBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idPerson = arguments?.getInt(KEY_PERSON)
        if (idPerson != null) {
            loadSingleCharacter(idPerson)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        load.cancel()

    }

    private fun loadSingleCharacter(id: Int) {

        load = RickAndMortyService.getInstance().getCharacter(id)
        load.enqueue(object : Callback<PersonInfo> {
            override fun onResponse(
                call: Call<PersonInfo>,
                response: Response<PersonInfo>,
            ) {
                if (response.isSuccessful) {
                    val person = response.body()

                    with(binding) {
                        if (person != null) {
                            avatar.load(person.image)
                            name.text = person.name
                            status.text = getString(R.string.status, person.status)
                            species.text = getString(R.string.species, person.species)
                            type.text = getString(R.string.type, person.type)
                            gender.text = getString(R.string.gender, person.gender)
                            location.text = getString(R.string.location, person.location.name)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<PersonInfo>, t: Throwable) {
                load.cancel()
            }
        })
    }

    companion object {
        const val KEY_PERSON_NAME = "personName"
        const val KEY_PERSON = "idPersonInfo"
    }
}

