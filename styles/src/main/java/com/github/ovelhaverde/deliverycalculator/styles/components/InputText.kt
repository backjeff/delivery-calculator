package com.github.ovelhaverde.deliverycalculator.styles.components

import android.content.Context
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.*
import androidx.core.widget.doAfterTextChanged
import com.github.ovelhaverde.deliverycalculator.styles.R
import com.github.ovelhaverde.deliverycalculator.styles.util.MoneyMask
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class InputText @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyle: Int = 0,
    private val defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    var handleClickEventsDebounced: (String) -> Unit = {}
    var debounceJob: Job? = null
    private var isStateError = false

    val layout: TextInputLayout
    val editText: TextInputEditText

    var text: String = ""
        get() = editText.text?.toString() ?: ""
        set(value) {
            field = value
            editText.setText(value)
            editText.setSelection(value.length)
            editText.performClick()
        }

    var hint = ""
        set(value) {
            field = value
            layout.hint = value
        }

    var error: String? = ""
        set(value) {
            field = value
            setErrorState()
        }

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.component_input_text, this, true).run {
                layout = findViewById(R.id.layout)
                editText = findViewById(R.id.editText)
            }
        setupStyleable()
    }

    private fun setupStyleable() {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.InputText,
            defStyle,
            defStyleRes
        ).apply {
            try {
                isEnabled = getBoolean(R.styleable.InputText_isEnabled, true)
                editText.setText(getString(R.styleable.InputText_android_text) ?: "")
                hint = getString(R.styleable.InputText_android_hint) ?: ""
            } finally {
                recycle()
            }
        }
    }

    fun setupViewAsMoney() {
        editText.keyListener = DigitsKeyListener.getInstance(".,0123456789")
        editText.addTextChangedListener(MoneyMask.getFormatter(editText))
        editText.inputType = InputType.TYPE_CLASS_NUMBER
    }

    fun doAfterTextChanged(event: (text: String) -> Unit) {
        editText.requestFocus()
        editText.doAfterTextChanged {
            event(it?.toString() ?: "")
        }
    }

    fun doDebounce(
        delayMs: Long = 1000L,
        event: (text: String) -> Unit)
    {
        editText.requestFocus()
        editText.doAfterTextChanged { editable ->
            cancelDebounce()
            handleClickEventsDebounced =
                debounce(delayMs, GlobalScope.coroutineContext) {
                    event(it)
                }
            handleClickEventsDebounced(editable.toString())
        }
    }

    private fun <T> debounce(delayMs: Long = 1000L,
                             coroutineContext: CoroutineContext,
                             f: (T) -> Unit): (T) -> Unit {
        debounceJob = null
        return { param: T ->
            if (debounceJob?.isCompleted != false) {
                debounceJob = CoroutineScope(coroutineContext).launch {
                    delay(delayMs)
                    f(param)
                }
            }
        }
    }

    fun cancelDebounce() = debounceJob?.cancel()

    private fun setErrorState() {
        if (error?.isBlank() == true) return

        isStateError = true

        editText.setHintTextColor(getColor(R.color.red))
        editText.setTextColor(getColor(R.color.red))
    }

    private fun setNormalState() {
        if (error == null) {
            setNormalState()
            return
        }

        if (!isStateError) return

        isStateError = false

        editText.setHintTextColor(getColor(R.attr.colorPrimary))
        editText.setTextColor(getColor(R.attr.colorPrimary))
    }

    fun handleSuccess(
        event: (text: String) -> Unit = {}
    ) {
        this.error = null
        cancelDebounce()
        event(this.text)
    }

    fun handleError(
        error: Throwable,
        event: (error: Throwable) -> Unit = {}
    ) {
        if(editText.text.toString().isEmpty()) {
            this.error = error.message
            cancelDebounce()
        } else {
            this.doDebounce {
                this.error = error.message
            }
        }
        event(error)
    }

    private fun getColor(id: Int) = getColor(context, id)

    private fun getDrawable(id: Int) = getDrawable(context, id)

}