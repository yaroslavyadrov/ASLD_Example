package com.example.asldexample.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.appcompat.widget.AppCompatImageButton
import com.example.asldexample.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AccountImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : AppCompatImageButton(context, attrs, defStyleAttr), CoroutineScope {

    override val coroutineContext = Dispatchers.Main

    private var animationJobs: MutableList<Job> = mutableListOf()

    var status = AccountViewState.NOTHING
        set(value) {
            if (field == value) return
            animationJobs.add(launch {
                field = AccountViewState.NOTHING
                refreshDrawableState()
                withContext(Dispatchers.Default) {
                    delay(
                        resources.getInteger(R.integer.animation_duration_half).toLong()
                    )
                }
                field = value
                refreshDrawableState()
            })
        }

    init {
        setImageResource(R.drawable.asld_account_drawable)
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        @Suppress("SENSELESS_COMPARISON") // Status is null during super init
        if (status == null) return super.onCreateDrawableState(extraSpace)
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        mergeDrawableStates(drawableState, status.state)
        return drawableState
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animationJobs.forEach {
            if (it.isActive) {
                it.cancel()
            }
        }
    }
}

enum class AccountViewState(val state: IntArray) {
    WALLET(intArrayOf(R.attr.wallet)),
    BALANCE(intArrayOf(R.attr.balance)),
    ADD_CARD(intArrayOf(R.attr.addCard)),
    NOTHING((intArrayOf(R.attr.nothing)))
}
