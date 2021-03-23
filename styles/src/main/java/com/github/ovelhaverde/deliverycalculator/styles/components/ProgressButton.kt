package com.github.ovelhaverde.deliverycalculator.styles.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.github.ovelhaverde.deliverycalculator.styles.R

class ProgressButton @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyle: Int = 0,
    private val defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private val progress: ProgressBar
    val button: Button

    fun setOnClickListener(listener: () -> Unit) {
        button.setOnClickListener {
            isLoading = true
            listener()
        }
    }

    var isLoading = false
        set(value) {
            field = value
            when (value) {
                true -> {
                    button.text = ""
                    button.isEnabled = false
                }
                false -> {
                    button.text = text
                    button.isEnabled = true
                }
            }
            progress.isVisible = value
        }

    var text: String = ""
        set(value) {
            field = value
            button.text = value
        }

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.component_progress_button, this, true).run {
                progress = findViewById(R.id.progress)
                button = findViewById(R.id.button)
            }
        setupStyleable()
    }

    private fun setupStyleable() {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ProgressButton,
            defStyle,
            defStyleRes
        ).apply {
            try {
                text = getString(R.styleable.ProgressButton_android_text) ?: ""
            } finally {
                recycle()
            }
        }
    }

}