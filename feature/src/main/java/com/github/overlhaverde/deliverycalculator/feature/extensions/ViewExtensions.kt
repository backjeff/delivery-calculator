package com.github.overlhaverde.deliverycalculator.feature.extensions

import android.text.InputType
import android.text.method.DigitsKeyListener
import android.widget.EditText
import com.github.overlhaverde.deliverycalculator.feature.util.MoneyMask

fun EditText.setupViewAsMoney() {
    keyListener = DigitsKeyListener.getInstance(".,0123456789")
    addTextChangedListener(MoneyMask.getFormatter(this))
    inputType = InputType.TYPE_CLASS_NUMBER
}