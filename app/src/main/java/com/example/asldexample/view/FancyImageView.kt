package com.example.asldexample.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.appcompat.widget.AppCompatImageButton
import com.example.asldexample.R

class FancyImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : AppCompatImageButton(context, attrs, defStyleAttr) {

    var status = FancyViewState.ADD
        set(value) {
            if (field == value) return
            refreshDrawableState()
            field = value
        }

    init {
        setImageResource(R.drawable.asld_fancy_drawable)
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        @Suppress("SENSELESS_COMPARISON") // Status is null during super init
        if (status == null) return super.onCreateDrawableState(extraSpace)
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        mergeDrawableStates(drawableState, status.state)
        return drawableState
    }
}

enum class FancyViewState(val state: IntArray) {
    ADD(intArrayOf(R.attr.add)),
    FORWARD(intArrayOf(R.attr.forward)),
}
