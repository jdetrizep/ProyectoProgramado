package com.proyecto.pokedex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.proyecto.pokedex.R
import com.proyecto.pokedex.databinding.FragmentLoginBinding
import com.proyecto.pokedex.models.Entrenador


class LoginFragment : Fragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEntrar.setOnClickListener{
            var nombre : String
            var generoMasculino : Boolean
            var generoFemenino : Boolean

            nombre = binding.txtEntrenador.text.toString()
            generoMasculino = binding.radioMasculino.isChecked
            generoFemenino = binding.radioFemenino.isChecked
            /*var action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(
                Entrenador(nombre,generoMasculino,generoFemenino)
            )*/

            var action = LoginFragmentDirections.actionLoginFragmentToMainFragment(
                Entrenador(nombre,generoMasculino,generoFemenino)
            )

            findNavController().navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}