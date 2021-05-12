package com.proyecto.pokedex.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.proyecto.pokedex.R
import com.proyecto.pokedex.databinding.FragmentMensajeBinding
import java.security.AccessControlContext

class MensajeFragment @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: FragmentMensajeBinding =
        FragmentMensajeBinding.inflate(LayoutInflater.from(context), this)

    init {
        inflate(context, R.layout.fragment_mensaje, this)

        attrs.let {
            val typedArray = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.MensajeFragment,
                defStyleAttr,
                defStyleRes
            )
            binding.txtMensaje.text = typedArray.getString(R.styleable.MensajeFragment_userMensaje)
            typedArray.recycle()
        }
    }
}//Fin de la Clase MensajeFragment