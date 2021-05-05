package com.proyecto.pokedex.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavArgument
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.proyecto.pokedex.R
import com.proyecto.pokedex.models.SharedViewModel


class MainFragment : Fragment(R.layout.fragment_main) {
    val arguments : MainFragmentArgs by navArgs()
    private lateinit var vBtnNavView : BottomNavigationView

    //Manejo para pasar datos entre Fragments
    private val model: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vBtnNavView = view.findViewById(R.id.btnNavView)
        val navHostFragment =
                childFragmentManager.findFragmentById(R.id.navHostFrag2) as NavHostFragment
        val navController = navHostFragment.navController

        //Cargo el Argument al Compartido de Fragments
        model.select(arguments.user)

        vBtnNavView.setupWithNavController(navController)

    }
}//Fin de la Clase MainFragment