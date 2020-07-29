package com.hsb.gyqwanandroid.util.extension

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorRes

/**
 *
 * @author gyq
 * @date 2020/7/28
 */
inline fun View.gone() {
    visibility = View.GONE
}

inline fun View.visible() {
    visibility = View.VISIBLE
}

inline fun View.invisible() {
    visibility = View.INVISIBLE
}

inline fun TextView.textColor(@ColorRes resId: Int) {
    setTextColor(resources.getColor(resId))
}

inline fun TextView.setDrawable(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) {
    setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}