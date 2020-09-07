package com.kalashnyk.denys.movaapp.utils.extensions

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun <A : Activity> A.hideKeyboard() {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    val windowToken = currentFocus?.windowToken
    if (windowToken != null) {
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }
}

fun <A : Activity> A.showToast(text: Any) = Toast.makeText(this, text.toString(), Toast.LENGTH_SHORT).show()

fun <A : Activity> A.showSnack(text: String) =
    Snackbar.make(this.findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG).show()