package com.proyecto.pokedex.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.proyecto.pokedex.R
import com.proyecto.pokedex.databinding.FragmentMensajeBinding
import java.security.AccessControlContext

class MensajeFragment @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: FragmentMensajeBinding =
        FragmentMensajeBinding.inflate(LayoutInflater.from(context), this)
    var imgTituloFav: String =
        "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/i/9eb15e31-3489-4272-a03c-6ed596738454/d7eg40h-68cb40bb-1fdb-436c-ae0b-8e3a16b041e9.png"

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

            Glide.with(context)
                .load(imgTituloFav)
                .circleCrop()
                .into(binding.imgMensage)

            typedArray.recycle()
        }
    }
}//Fin de la Clase MensajeFragment