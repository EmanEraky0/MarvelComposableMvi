package com.example.marvelcomposablemvi.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import java.security.MessageDigest

fun generateHash(ts: String, privateKey: String, publicKey: String): String {
    val input = "$ts$privateKey$publicKey"
    return MessageDigest.getInstance("MD5").digest(input.toByteArray())
        .joinToString("") { "%02x".format(it) }
}



fun Fragment.hideKeyboard() {
    val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = requireActivity().currentFocus ?: View(requireActivity())
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}


