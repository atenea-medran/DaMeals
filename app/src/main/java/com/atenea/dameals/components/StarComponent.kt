package com.atenea.dameals.components

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import com.atenea.dameals.R

class StarComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val imageView: ImageView
    var checked: Boolean = false
        set(value) {
            field = value
            selectImage()
        }

    init {
        imageView = inflate(context, R.layout.component_checkbox, this)
            .findViewById(R.id.iv_checkbox)
    }

    private fun selectImage() = imageView.setImageResource(
        if (checked) {
            R.drawable.done
        } else {
            R.drawable.empty_checkbox
        }
    )
}