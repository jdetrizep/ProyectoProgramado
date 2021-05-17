package com.proyecto.pokedex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding4.widget.textChanges
import com.proyecto.pokedex.R
import com.proyecto.pokedex.databinding.FragmentLoginBinding
import com.proyecto.pokedex.models.Entrenador
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.BiFunction
import java.util.*
import java.util.concurrent.TimeUnit


class LoginFragment : Fragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    //RX - Reactiva
    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEntrar.setOnClickListener {
            var nombre: String
            var generoMasculino: Boolean
            var generoFemenino: Boolean

            nombre = binding.txtEntrenador.text.toString()
            generoMasculino = binding.radioMasculino.isChecked
            generoFemenino = binding.radioFemenino.isChecked
            /*var action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(
                Entrenador(nombre,generoMasculino,generoFemenino)
            )*/

            var action = LoginFragmentDirections.actionLoginFragmentToMainFragment(
                Entrenador(nombre, generoMasculino, generoFemenino)
            )

            findNavController().navigate(action)
        }

        //FORMA RX - REACTIVA
        disposable.add(
            binding.txtEntrenador.textChanges()
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                .map { it.toString() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    binding.nombreEditText.error = if (it.isEmpty()) "Campo Requerido" else null
                }
        )

        disposable.add(
            binding.txtEntrenador.textChanges()
                .map { it.toString() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    binding.btnEntrar.isEnabled = it.isNotEmpty()
                }
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        disposable.clear()
    }
}